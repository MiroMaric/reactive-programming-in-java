package rs.miromaric.plutus.user.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.exception.SystemUserNotFoundException;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.user.service.SystemUserService;

import java.net.URI;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@Slf4j
public class SystemUserController {

    private final SystemUserService service;

    @PostMapping
    public Mono<ResponseEntity<SystemUser>> save(@RequestBody SystemUser systemUser) {
        return service.save(systemUser).map(user -> ResponseEntity
                .created(URI.create("/api/v1/users/" + user.getUuid()))
                .body(user));
    }

    @GetMapping("/{id}")
    public Mono<SystemUser> get(@PathVariable("id") String uuid) {
        return service.get(uuid).switchIfEmpty(
                Mono.error(SystemUserNotFoundException.withUuid(uuid)));
    }

    @PutMapping("/{id}")
    public Mono<SystemUser> update(@RequestBody SystemUser systemUser, @PathVariable("id") String uuid) {
        systemUser.setUuid(uuid);
        return service.update(systemUser).switchIfEmpty(
                Mono.error(SystemUserNotFoundException.withUuid(uuid)));
    }

    @DeleteMapping("/{id}")
    public Mono<SystemUser> delete(@PathVariable("id") String uuid) {
        return service.delete(uuid).switchIfEmpty(
                Mono.error(SystemUserNotFoundException.withUuid(uuid)));
    }

}
