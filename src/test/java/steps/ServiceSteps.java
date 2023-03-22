package steps;

import com.google.gson.Gson;
import com.peterservice.pstest.common.Customer;
import io.qameta.allure.Step;

import static confiuration.ConfigProvider.BASE_URL;
import static io.restassured.RestAssured.given;
import static specifications.Specifications.*;

public class ServiceSteps {
    @Step("Create new customer")
    public Customer createCustomer(Customer customer, String endpoint) {
        installSpecification(requestSpecification(BASE_URL), responseSpec(201));

        String responseMessage = given()
                .body(customer)
                .post(endpoint)
                .then()
                .log().all()
                .extract().asString();
        return new Gson().fromJson(responseMessage, Customer.class);
    }
}