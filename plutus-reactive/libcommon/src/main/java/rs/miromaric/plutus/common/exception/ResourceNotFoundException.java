package rs.miromaric.plutus.common.exception;

public class ResourceNotFoundException extends PlutusException {

    private static final String MESSAGE_ID = "resource.exception.not-found.id";

    public ResourceNotFoundException(Object id) {
        super(MESSAGE_ID, id);
    }

    public ResourceNotFoundException(String message, Object... args) {
        super(message, args);
    }

}
