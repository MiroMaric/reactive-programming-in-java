package rs.miromaric.plutus.payment.service.proxy;

import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.model.SystemUser;

public interface SystemUserApi {
    Mono<SystemUser> get(String id);
}
