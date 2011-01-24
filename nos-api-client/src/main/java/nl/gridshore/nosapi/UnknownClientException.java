package nl.gridshore.nosapi;

/**
 * <p>Exception used to indicate something unexpected and unanticipated has happened.</p>
 * @author Jettro Coenradie
 */
public class UnknownClientException extends RuntimeException {
    public UnknownClientException(String s) {
        super(s);
    }

    public UnknownClientException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
