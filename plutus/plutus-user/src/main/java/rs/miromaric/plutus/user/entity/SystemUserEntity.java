package rs.miromaric.plutus.user.entity;

import lombok.Getter;
import lombok.Setter;
import rs.miromaric.plutus.common.entity.PlutusEntity;
import rs.miromaric.plutus.common.entity.PlutusEntityId;
import rs.miromaric.plutus.user.model.ImmutableSystemUser;
import rs.miromaric.plutus.user.model.SystemUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "SYSTEM_USER", indexes = {
        @Index(name = "system_user_username_idx", columnList = "username", unique = true)
})
@Getter
@Setter
public class SystemUserEntity extends PlutusEntityId implements PlutusEntity<SystemUser> {
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstname;
    @Column(name = "LAST_NAME", nullable = false)
    private String lastname;

    @SuppressWarnings("unused")
    public SystemUserEntity() { }

    public SystemUserEntity(SystemUser user) {
        this.uuid = user.getUuid() ;
        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
    }

    @Override
    public SystemUserEntity update(SystemUser defaultDto) {
        this.username = defaultDto.getUsername();
        this.firstname = defaultDto.getFirstname();
        this.lastname = defaultDto.getLastname();
        return this;
    }

    @Override
    public SystemUser getDefaultDto() {
        return ImmutableSystemUser
                .builder()
                .uuid(this.uuid)
                .username(this.username)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .build();
    }
}
