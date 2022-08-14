package rs.miromaric.plutus.user.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import rs.miromaric.plutus.user.model.SystemUser;
import rs.miromaric.plutus.user.service.SystemUserService;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@Slf4j
public class SystemUserController {

    private final SystemUserService systemUserService;

    @PostMapping
    public SystemUser save(@RequestBody SystemUser systemUser) {
        return systemUserService.save(systemUser);
    }

    @GetMapping("/{id}")
    public SystemUser get(@PathVariable("id") String id) {
        return systemUserService.get(id);
    }

    @PutMapping("/{id}")
    public SystemUser get(@RequestBody SystemUser systemUser, @PathVariable("id") String id) {
        systemUser = systemUser.copy().withUuid(id);
        return systemUserService.update(systemUser);
    }

    @DeleteMapping("/{id}")
    public SystemUser delete(@PathVariable("id") String id) {
        return systemUserService.delete(id);
    }

}
