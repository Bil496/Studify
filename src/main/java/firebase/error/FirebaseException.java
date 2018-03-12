package firebase.error;

import org.apache.log4j.Logger;

/**
 * Created by ask on 13.03.2018
 */
public class FirebaseException extends Throwable {
    protected static final Logger LOGGER = Logger.getRootLogger();

    private static final long serialVersionUID = 1L;

    public FirebaseException( String message ) {
        super( message );
    }

    public FirebaseException( String message, Throwable cause ) {
        super( message, cause );
    }
}
