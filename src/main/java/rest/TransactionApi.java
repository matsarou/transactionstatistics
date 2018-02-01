package rest;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The REST API methods, responsible to expose data to the clients.
 */
@Path("")
public interface TransactionApi {

    @POST
    @Path("/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response createTransaction(PostTransactionRequest request);

    @GET
    @Path("/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    Response getTransaction();

    @GET
    @Path("/statistics")
    @Produces(MediaType.APPLICATION_JSON)
    Response getTransactionStatistics();

}
