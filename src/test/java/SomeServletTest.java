import com.peterservice.pstest.SomeServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Created by Andrey.Shilov on 18.11.2015.
 */
public class SomeServletTest {
    private static final Server CUSTOMERS_SERVER = new Server(8088);

    public static void setup() throws Exception {
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(SomeServlet.class, "/*");
        CUSTOMERS_SERVER.setHandler(servletHandler);
        CUSTOMERS_SERVER.start();
    }

    public static void stop() throws Exception {
        CUSTOMERS_SERVER.stop();
    }

}
