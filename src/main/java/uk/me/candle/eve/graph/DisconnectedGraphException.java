package uk.me.candle.eve.graph;

/**
 *
 * @author Candle
 */
public class DisconnectedGraphException extends RuntimeException {
    private static final long serialVersionUID = 1l;

    public DisconnectedGraphException(Throwable cause) {
        super(cause);
    }

    public DisconnectedGraphException(String message, Throwable cause) {
        super(message, cause);
    }

    public DisconnectedGraphException(String message) {
        super(message);
    }

    public DisconnectedGraphException() {
    }
}
