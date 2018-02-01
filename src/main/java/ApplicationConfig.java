import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import rest.TransactionApiImpl;

/**
 * Provides the base URI of the resource.
 */
@ApplicationPath("rest")
public class ApplicationConfig extends Application {
}