package rs.miromaric.plutus.payment.model.payin;

public enum PayInStatus {
    COMPLETED,
    USER_NOT_FOUND,
    USER_INACTIVE,
    WALLET_NOT_FOUND,
    WALLET_INACTIVE,
    WALLET_WRONG,
    FAILED
}
