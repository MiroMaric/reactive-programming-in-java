package rs.miromaric.plutus.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.entity.SystemUserEntity;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.user.repository.DefaultSystemUserRepository;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SystemUserServiceImpl implements SystemUserService {

    private final DefaultSystemUserRepository repository;

    @Override
    public Mono<SystemUser> save(SystemUser user) {
        return repository.save(new SystemUserEntity(user)).map(SystemUserEntity::getDefaultDto);
    }

    @Override
    public Mono<SystemUser> get(String uuid) {
        return repository.findById(uuid).map(SystemUserEntity::getDefaultDto);
    }

    @Override
    public Mono<SystemUser> update(SystemUser user) {
        return repository.findById(user.getUuid())
                .map(entity -> entity.update(user))
                .flatMap(entity -> repository.save(entity).map(SystemUserEntity::getDefaultDto));
    }

    @Override
    public Mono<SystemUser> delete(String uuid) {
        return get(uuid)
                .flatMap(user -> repository.deleteById(user.getUuid())
                        .thenReturn(user));
    }
}
