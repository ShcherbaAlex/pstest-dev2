package tests;

import com.peterservice.pstest.SomeServlet;
import com.peterservice.pstest.common.Customer;
import com.peterservice.pstest.common.Subscriber;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.ServiceSteps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static constants.Constants.PSTEST_CUSTOMERS;
import static java.util.Collections.singletonList;

/**
 * Created by Andrey.Shilov on 18.11.2015.
 */
public class SomeServletTest {
    private static final Server CUSTOMERS_SERVER = new Server(8088);
    private final ServiceSteps serviceSteps;

    public SomeServletTest() {
        serviceSteps = new ServiceSteps();
    }

    @BeforeClass
    public static void setup() {
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(SomeServlet.class, "/*");
        CUSTOMERS_SERVER.setHandler(servletHandler);
        try {
            CUSTOMERS_SERVER.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test(description = "")
    public void checkCreateCustomer() throws InterruptedException {
        Subscriber subscriber = new Subscriber();
        subscriber.setMsisdn("1234567");

        Customer customer = new Customer();
        customer.setName("Денис");
        customer.setCity("Ижевск");
        customer.setSubscribers(singletonList(subscriber));

        Customer response = serviceSteps.createCustomer(customer, PSTEST_CUSTOMERS);
        System.out.println(response.getName());
        Thread.sleep(10000);

    }

    @AfterClass
    public static void stop() {
        try {
            CUSTOMERS_SERVER.stop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
