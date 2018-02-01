package dao;

import domain.Transaction;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.persistence.criteria.CriteriaBuilder;

@Named("transactionDao")
public class TransactionDaoImpl implements TransactionDao {

    private EntityManager em;

    @Inject
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Transaction create(Transaction item) {
        try {
            em.getTransaction().begin();
            //em.lock(Transaction.class, LockModeType.PESSIMISTIC_WRITE);
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception exc) {

        }
        return item;
    }

    @Override
    public List<Transaction> findTransactionsByTime(long from, long to) {
        try {
            CriteriaBuilder qb = em.getCriteriaBuilder();
            CriteriaQuery cq = qb.createQuery();
            Root<Transaction> transaction = cq.from(Transaction.class);
            cq.select(transaction).where(
                    qb.between(transaction.get("timestamp"), from, to));
            return em.createQuery(cq)
                    //.setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }

    @Override
    public List<Transaction> findTransactions() {
        try {
            return em.createQuery("SELECT t FROM Transaction t")
                    .getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }
}
