package constants;

import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public final class Constants {
    private Constants() {
    }

    public static final String PSTEST_CUSTOMERS = "pstest/customers";

    public static final String MSISDN = randomNumeric(7);

    public static final String CUSTOMER_ID = "customerId";
}