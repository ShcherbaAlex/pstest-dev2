package statuscodes;

public enum StatusCode {
    CREATED(201),
    SERVER_ERROR(500),
    OK(200),
    NOT_FOUND(404);

    private final int statusCode;

    StatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}