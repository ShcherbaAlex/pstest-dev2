package tests;

import com.github.javafaker.Faker;
import com.peterservice.pstest.SomeServlet;
import com.peterservice.pstest.common.Customer;
import com.peterservice.pstest.common.Subscriber;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.ServiceSteps;

import static constants.Constants.MSISDN;
import static constants.Constants.PSTEST_CUSTOMERS;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static statuscodes.StatusCode.*;

/**
 * Created by Andrey.Shilov on 18.11.2015.
 */
public class SomeServletTest {
    private static final Server CUSTOMERS_SERVER = new Server(8088);
    private final ServiceSteps serviceSteps;
    private final Faker faker;
    private Customer customer;

    public SomeServletTest() {
        serviceSteps = new ServiceSteps();
        faker = new Faker();
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

    //positive test
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "check creating new customer")
    public void checkCreateCustomer() {
        Subscriber subscriber = new Subscriber();
        subscriber.setMsisdn(MSISDN);

        customer = new Customer();
        customer.setName(faker.name().firstName());
        customer.setCity(faker.address().city());
        customer.setSubscribers(singletonList(subscriber));

        serviceSteps.createCustomer(customer, PSTEST_CUSTOMERS, CREATED.getStatusCode());
    }

    //positive test
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "check getting customer by customerId", dependsOnMethods = "checkCreateCustomer")
    public void checkGetCustomerByCustomerId() {
        Customer response = serviceSteps.getCustomerByCustomerId(1L, PSTEST_CUSTOMERS, OK.getStatusCode());
        assertThat(customer.getName(), equalTo(response.getName()));
        assertThat(customer.getCity(), equalTo(response.getCity()));
        assertThat(customer.getSubscribers().get(0).getMsisdn(), equalTo(response.getSubscribers().get(0).getMsisdn()));
    }

    //negative test
    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "check getting customer by non exist customerId")
    public void checkGetCustomerByNonExistCustomerId() {
        serviceSteps.getCustomerByNonExistCustomerId(1000L, PSTEST_CUSTOMERS, NOT_FOUND.getStatusCode());
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