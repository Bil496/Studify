package firebase.error;

import org.apache.log4j.Logger;

/**
 * Created by ask on 13.03.2018
 */
public class JacksonUtilityException extends Throwable {
    protected static final Logger LOGGER = Logger.getRootLogger();

    private static final long serialVersionUID = 1L;

    public JacksonUtilityException(String message) {
        super(message);
    }

    public JacksonUtilityException(String message, Throwable cause) {
        super(message, cause);
    }
}
