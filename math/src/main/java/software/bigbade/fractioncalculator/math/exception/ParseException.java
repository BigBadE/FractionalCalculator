package software.bigbade.fractioncalculator.math.exception;

/**
 * The exception thrown when a parse error is found (no second value, divide by 0, etc...)
 */
public class ParseException extends RuntimeException {
    public ParseException(String error) {
        super(error);
    }
}
