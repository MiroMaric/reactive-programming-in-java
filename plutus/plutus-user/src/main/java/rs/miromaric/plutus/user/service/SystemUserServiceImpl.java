package rs.miromaric.plutus.user.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.miromaric.plutus.user.entity.SystemUserEntity;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.user.repository.SystemUserRepository;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {

    private final SystemUserRepository repository;

    @Override
    public SystemUser save(SystemUser user) {
        SystemUserEntity entity = new SystemUserEntity(user);
        return repository.save(entity).getDefaultDto();
    }

    @Override
    public SystemUser get(String uuid) {
        return repository.getByUuid(uuid).getDefaultDto();
    }

    @Override
    public SystemUser update(SystemUser user) {
        SystemUserEntity entity = repository.getByUuid(user.getUuid());
        entity.update(user);
        return repository.save(entity).getDefaultDto();
    }

    @Override
    public SystemUser delete(String uuid) {
        SystemUser systemUser = get(uuid);
        repository.deleteById(uuid);
        return systemUser;
    }

}
