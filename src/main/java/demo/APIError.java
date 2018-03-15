package demo;

public class APIError {

    private Integer statusCode;
    private String message;

    public APIError(Integer statusCode, String message) {
	    this.statusCode = statusCode;
	    this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}