package rs.miromaric.plutus.common.exception;

public class PlutusException extends RuntimeException {

    protected String message;
    protected Object[] args;

    public PlutusException(String message, Object... args) {
        this.message = message;
        this.args = args;
    }

}
