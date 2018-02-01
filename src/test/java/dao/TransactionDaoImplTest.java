package dao;

import domain.Transaction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jglue.cdiunit.CdiRunner;
import org.junit.*;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
public class TransactionDaoImplTest extends dao.IntegrationTestBaseClass {

    private static TransactionDaoImpl dao;
    private static List<Transaction> persistedTransactions;

    @BeforeClass
    public static void init() {
        dao = new TransactionDaoImpl();
        dao.setEntityManager(em);
        persistedTransactions = new ArrayList<>(Arrays.asList(
                persistTransaction(12.3, 1515957500399L),
                persistTransaction(5.2, 1215957500233L)));
    }

    @Test
    public void testCreateTransaction_Success() {
        assertPersistedTransaction(persistedTransactions.get(0), 12.3, 1515957500399L);
        assertPersistedTransaction(persistedTransactions.get(1),5.2, 1215957500233L);
    }

    @Test
    public void testRetrieveTransaction_by_time_Success() {
        List<Transaction> transactions = dao.findTransactionsByTime(1515957453385L, 1515957525631L);
        Assert.assertNotNull(transactions);
        Assert.assertTrue(transactions.size() == 1);
        assertPersistedTransaction(transactions.get(0), 12.3, 1515957500399L);
    }

    private static Transaction persistTransaction(final double amount, final long timestamp) {
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(amount);
        newTransaction.setTimestamp(timestamp);
        Transaction persistedTransaction = dao.create(newTransaction);
        return persistedTransaction;
    }

    public void assertPersistedTransaction(Transaction transaction, final double expectedAmount,
                                           final long expectedTimestamp) {
        Assert.assertNotNull(transaction);
        Assert.assertTrue(expectedAmount == transaction.getAmount());
        Assert.assertTrue(expectedTimestamp == transaction.getTimestamp());
    }
}
