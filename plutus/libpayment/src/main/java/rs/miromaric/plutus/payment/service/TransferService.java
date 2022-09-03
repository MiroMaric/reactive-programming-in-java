package rs.miromaric.plutus.payment.service;

import rs.miromaric.plutus.payment.model.Transfer;

public interface TransferService {
    Transfer save(Transfer transfer);
}
