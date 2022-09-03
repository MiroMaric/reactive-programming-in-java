package rs.miromaric.plutus.payment.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.miromaric.plutus.payment.entity.TransferEntity;
import rs.miromaric.plutus.payment.model.Transfer;
import rs.miromaric.plutus.payment.repository.TransferRepository;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Override
    public Transfer save(Transfer transfer) {
        TransferEntity transferEntity = new TransferEntity(transfer);
        return transferRepository.save(transferEntity).getDefaultDto();
    }
}
