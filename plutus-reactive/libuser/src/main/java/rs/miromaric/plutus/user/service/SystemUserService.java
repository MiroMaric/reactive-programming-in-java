package rs.miromaric.plutus.user.service;

import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.model.SystemUser;

public interface SystemUserService {
    Mono<SystemUser> save(SystemUser user);
    Mono<SystemUser> get(String uuid);
    Mono<SystemUser> update(SystemUser user);
    Mono<SystemUser> delete(String uuid);
}
