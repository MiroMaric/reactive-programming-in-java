package rs.miromaric.plutus.payment.model.payout;

public enum PayOutStatus {
    COMPLETED,
    INACTIVE_USER,
    INACTIVE_WALLET,
    WRONG_WALLET,
    NOT_ENOUGH_FUNDS,
    FAILED
}
