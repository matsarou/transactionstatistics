package service;

import dao.TransactionDao;
import domain.Transaction;
import dto.TransactionsStatisticsDto;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

@Named("transactionService")
public class TransactionServiceImpl implements TransactionService {

    private TransactionDao transactionDao;

    private StatisticsMapper statisticsMapper;

    @Override
    public Transaction createTransaction(double amount, long timestamp) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(timestamp);
        return transactionDao.create(transaction);
    }

    @Override
    public List<Transaction> findTransactions() {
        return transactionDao.findTransactions();
    }

    @Override
    public TransactionsStatisticsDto getTransactionsStatistics(Instant timeRequest) {
        List<Transaction> transactions = transactionDao
                .findTransactionsByTime(timeRequest.minusSeconds(60).toEpochMilli(), timeRequest.toEpochMilli());
        if(transactions.isEmpty()) {
            return null;
        } else {
            return statisticsMapper
                    .mapTransactionsToTransactionsStatististicsDto(transactions);
        }
    }

    @Inject
    public void setTransactionDao(@Named("transactionDao") TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Inject
    public void setStatisticsMapper(@Named("statisticsMapper") StatisticsMapper statisticsMapper) {
        this.statisticsMapper = statisticsMapper;
    }

    public static void main(String [ ] args)
    {
        Instant now = Instant.now();
        Timestamp timestamp = Timestamp.from(now);
        System.out.println("Instant = "+now);
        System.out.println("Instant(long) = "+now.toEpochMilli());
        System.out.println("Timestamp = "+timestamp);
        System.out.println("Timestamp(long) = "+timestamp.getTime());
        System.out.println(Timestamp.from(now.minusSeconds(5))
                .getTime());
    }
}
