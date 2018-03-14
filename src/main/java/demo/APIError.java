package demo;

public class APIError {

    private int statusCode;
    private String message;

    public APIError(int statusCode, String message) {
	this.statusCode = statusCode;
	this.message = message;
    }

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }
    
}