package rs.miromaric.plutus.user.exception;

import rs.miromaric.plutus.common.exception.ResourceNotFoundException;

public class SystemUserNotFoundException extends ResourceNotFoundException {

    private static final String MESSAGE_ID = "user.exception.not-found.id";

    public SystemUserNotFoundException(String message, Object... args) {
        super(message, args);
    }

    public static SystemUserNotFoundException withUuid(String uuid) {
        return new SystemUserNotFoundException(MESSAGE_ID, uuid);
    }
}
