package rs.miromaric.plutus.wallet.exception;

import rs.miromaric.plutus.common.exception.ResourceNotFoundException;

public class WalletNotFoundException extends ResourceNotFoundException {

    private static final String MESSAGE_ID = "wallet.exception.not-found.id";

    public WalletNotFoundException(String message, Object... args) {
        super(message, args);
    }

    public static WalletNotFoundException withId(String uuid) {
        return new WalletNotFoundException("", uuid);
    }
}
