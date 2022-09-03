package rs.miromaric.plutus.wallet.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.miromaric.plutus.user.model.SystemUser;

@FeignClient(name = "system-user-service", url = "${plutus.wallet.user-service-url}")
public interface SystemUserService {

    @GetMapping("/{id}")
    SystemUser get(@PathVariable("id") String id);

}
