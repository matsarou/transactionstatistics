package rest;

import domain.Transaction;
import dto.TransactionsStatisticsDto;
import java.time.Instant;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.TransactionService;

public class TransactionApiImpl implements TransactionApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionApiImpl.class);

    private TransactionService transactionService;

    @Inject
    public void setTransactionService(@Named("transactionService") TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public Response createTransaction(PostTransactionRequest request) {
        Transaction newTransaction = transactionService.createTransaction(request.getAmount(), request.getTimestamp());
        Response.Status responseStatus;
        if(newTransaction == null) {
            responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
        } else {
            responseStatus = isLastMinuteTransaction(newTransaction.getTimestamp(), Instant.now())
                    ? Response.Status.CREATED
                    : Response.Status.NO_CONTENT;
        }
        return Response.status(responseStatus)
                .build();
    }

    @Override
    public Response getTransaction() {
        List<Transaction> transactions = transactionService.findTransactions();
        return Response.status(Response.Status.OK)
                .entity(transactions)
                .build();
    }

    @Override
    public Response getTransactionStatistics() {
        TransactionsStatisticsDto statistics = transactionService.getTransactionsStatistics(Instant.now());
        return Response.status(Response.Status.OK)
                .entity(statistics)
                .build();
    }

    private boolean isLastMinuteTransaction(long transactionTime, Instant now) {
        return transactionTime <= now.toEpochMilli()
                && transactionTime >= now.minusSeconds(60).toEpochMilli();
    }
}
