package rs.miromaric.plutus.user.mapper;

import rs.miromaric.plutus.user.entity.SystemUserEntity;
import rs.miromaric.plutus.user.model.ImmutableSystemUser;
import rs.miromaric.plutus.user.model.SystemUser;

public class SystemUserMapper {

    public static SystemUser toDefaultDto(SystemUserEntity entity) {
        return ImmutableSystemUser
                .builder()
                .uuid(entity.getUuid())
                .username(entity.getUsername())
                .firstname(entity.getFirstname())
                .lastname(entity.getLastname())
                .build();
    }

}
