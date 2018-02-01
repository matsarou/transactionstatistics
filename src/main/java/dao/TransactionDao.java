package dao;

import domain.Transaction;
import java.util.List;

public interface TransactionDao {

    Transaction create(Transaction item);

    List<Transaction> findTransactionsByTime(long from, long to);

    List<Transaction> findTransactions();
}
