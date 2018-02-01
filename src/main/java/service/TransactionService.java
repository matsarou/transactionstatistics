package service;

import domain.Transaction;
import dto.TransactionsStatisticsDto;
import java.time.Instant;
import java.util.List;

public interface TransactionService {

    Transaction createTransaction(double amount, long timestamp);

    List<Transaction> findTransactions();

    TransactionsStatisticsDto getTransactionsStatistics(Instant timeRequest);
}
