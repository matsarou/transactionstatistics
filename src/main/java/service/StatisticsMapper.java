package service;

import domain.Transaction;
import dto.TransactionsStatisticsDto;
import java.util.List;
import javax.inject.Named;

@Named("statisticsMapper")
public class StatisticsMapper {

    TransactionsStatisticsDto mapTransactionsToTransactionsStatististicsDto(List<Transaction> transactions) {
        TransactionsStatisticsDto dto = new TransactionsStatisticsDto();
        dto.setSum(getSum(transactions));
        dto.setAvg(getAvg(transactions));
        dto.setMin(getMin(transactions));
        dto.setMax(getMax(transactions));
        dto.setCount(transactions.size());
        return dto;
    }

    private double getSum(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(trans -> trans.getAmount())
                .sum();
    }

    private double getAvg(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(trans -> trans.getAmount())
                .average()
                .getAsDouble();
    }

    private double getMin(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(trans -> trans.getAmount())
                .min()
                .getAsDouble();
    }

    private double getMax(List<Transaction> transactions) {
        return transactions.stream()
                .mapToDouble(trans -> trans.getAmount())
                .max()
                .getAsDouble();
    }

}
