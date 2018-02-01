package rest;

import dao.IntegrationTestBaseClass;
import dto.TransactionsStatisticsDto;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.core.Response;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
public class TransactionApiImplTest extends IntegrationTestBaseClass {

    private static List<Response> persistenceResponses;

    @BeforeClass
    public static void init() {
        persistenceResponses = new ArrayList<>(Arrays.asList(
                persistTransaction(12.3, Instant.now().toEpochMilli()),
                persistTransaction(5.2, 1215957500233L),
                persistTransaction(24.1, Instant.now().toEpochMilli()),
                persistTransaction(18.5, Instant.now().toEpochMilli()))
        );
    }

    @Test
    public void testCreateTransaction_Success() {
        assertHttpResponse(persistenceResponses.get(0), 201);
        assertHttpResponse(persistenceResponses.get(1),204);
        assertHttpResponse(persistenceResponses.get(2), 201);
        assertHttpResponse(persistenceResponses.get(3), 201);
    }

    private void assertHttpResponse(Response response, int status) {
        Assert.assertNotNull(response);
        Assert.assertNull(response.getEntity());
        Assert.assertTrue(status == response.getStatus());
    }

    @Test
    public void testRetrieveTransaction_by_time_Success() {
        Response response = api.getTransactionStatistics();
        Assert.assertNotNull(response);
        TransactionsStatisticsDto statistics = (TransactionsStatisticsDto) response.getEntity();
        Assert.assertTrue("the total sum of transaction value in the last 60 seconds",
                12.3 + 24.1 + 18.5 == statistics.getSum());
        Assert.assertTrue("the average amount of transaction value in the last 60\n" +
                "seconds",18.3 == statistics.getAvg());
        Assert.assertTrue("the single highest transaction value in the last 60 seconds",
                24.1 == statistics.getMax());
        Assert.assertTrue("single lowest transaction value in the last 60 seconds",
                12.3 == statistics.getMin());
        Assert.assertTrue("the total number of transactions happened in the last 60\n" +
                "seconds",3 == statistics.getCount());
    }

    private static Response persistTransaction(final double amount, final long timestamp) {
        PostTransactionRequest request = new PostTransactionRequest();
        request.setAmount(amount);
        request.setTimestamp(timestamp);
        Response persistedTransaction = api.createTransaction(request);
        return persistedTransaction;
    }
}
