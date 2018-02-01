package dao;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import rest.TransactionApiImpl;
import service.StatisticsMapper;
import service.TransactionServiceImpl;

public class IntegrationTestBaseClass {

    @Produces
    private static EntityManagerFactory emf;

    protected static EntityManager em;
    protected static TransactionApiImpl api;
    private static TransactionServiceImpl service;
    private static TransactionDaoImpl dao;
    private static StatisticsMapper statisticsMapper;

    @BeforeClass
    public static void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("hsqldb");
        em = emf.createEntityManager();

        api = new TransactionApiImpl();
        statisticsMapper = new StatisticsMapper();
        service = new TransactionServiceImpl();
        dao = new TransactionDaoImpl();
        dao.setEntityManager(em);
        service.setTransactionDao(dao);
        service.setStatisticsMapper(statisticsMapper);
        api.setTransactionService(service);
    }

    @AfterClass
    public static void closeEntityManagerFactory() {
        emf.close();
    }

}
