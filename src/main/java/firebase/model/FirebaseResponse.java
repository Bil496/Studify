package firebase.model;

import org.apache.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ask on 13.03.2018
 */
public class FirebaseResponse {

    private static final Logger LOGGER = Logger.getRootLogger();

    // PROPERTIES & CONSTRUCTORS
    private final boolean success;
    private final int code;
    private final Map<String, Object> body;
    private final String rawBody;

    public FirebaseResponse(boolean success, int code, Map<String, Object> body, String rawBody) {

        this.success = success;
        this.code = code;

        if (body == null) {
            LOGGER.info("body was null; replacing with empty map");
            body = new LinkedHashMap<>();
        }
        this.body = body;

        if (rawBody == null) {
            LOGGER.info("rawBody was null; replacing with empty string");
            rawBody = "";
        }
        this.rawBody = rawBody.trim();
    }

    // PUBLIC API

    /**
     * Returns whether or not the response from the Firebase-client was successful
     *
     * @return true if response from the Firebase-client was successful
     */
    public boolean getSuccess() {
        return this.success;
    }

    /**
     * Returns the HTTP status code returned from the Firebase-client
     *
     * @return an integer representing an HTTP status code
     */
    public int getCode() {
        return this.code;
    }

    /**
     * Returns a map of the data returned by the Firebase-client
     *
     * @return a map of Strings to Objects
     */
    public Map<String, Object> getBody() {
        return this.body;
    }

    /**
     * Returns the raw data response returned by the Firebase-client
     *
     * @return a String of the JSON-response from the client
     */
    public String getRawBody() {
        return this.rawBody;
    }

    @Override
    public String toString() {

        return FirebaseResponse.class.getSimpleName() + "[ " +
                "(Success:" + this.success + ") " +
                "(Code:" + this.code + ") " +
                "(Body:" + this.body + ") " +
                "(Raw-body:" + this.rawBody + ") " +
                "]";
    }
}
