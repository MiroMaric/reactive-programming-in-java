package rs.miromaric.plutus.user.service;

import rs.miromaric.plutus.user.model.SystemUser;

public interface SystemUserService {
    SystemUser save(SystemUser user);
    SystemUser get(String uuid);
    SystemUser update(SystemUser user);
    SystemUser delete(String uuid);
}
