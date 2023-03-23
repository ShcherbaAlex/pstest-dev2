package steps;

import com.peterservice.pstest.common.Customer;
import io.qameta.allure.Step;

import static confiuration.ConfigProvider.BASE_URL;
import static constants.Constants.CUSTOMER_ID;
import static io.restassured.RestAssured.given;
import static specifications.Specifications.*;

public class ServiceSteps {
    @Step("Create new customer")
    public void createCustomer(Customer customer, String endpoint, int expectedStatusCode) {
        installSpecification(requestSpecification(BASE_URL), responseSpec(expectedStatusCode));

        given()
                .body(customer)
                .post(endpoint)
                .then()
                .log().all();
    }

    @Step("Get customer by customerId")
    public Customer getCustomerByCustomerId(Long customerId, String endpoint, int expectedStatusCode) {
        installSpecification(requestSpecification(BASE_URL), responseSpec(expectedStatusCode));

        return given()
                .queryParam(CUSTOMER_ID, customerId)
                .get(endpoint)
                .then()
                .log().all()
                .extract().response().as(Customer.class);
    }

    @Step("Get customer by non exist customerId")
    public void getCustomerByNonExistCustomerId(Long customerId, String endpoint, int expectedStatusCode) {
        installSpecification(requestSpecification(BASE_URL), responseSpec(expectedStatusCode));

        given()
                .queryParam(CUSTOMER_ID, customerId)
                .get(endpoint)
                .then()
                .log().all();
    }
}