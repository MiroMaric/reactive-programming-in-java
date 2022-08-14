package rs.miromaric.plutus.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import rs.miromaric.plutus.common.entity.PlutusEntity;
import rs.miromaric.plutus.common.entity.VersionedPlutusEntity;
import rs.miromaric.plutus.user.model.SystemUser;

@Table("SYSTEM_USER")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemUserEntity extends VersionedPlutusEntity implements PlutusEntity<SystemUser> {

    private String username;
    @Column("first_name")
    private String firstname;
    @Column("last_name")
    private String lastname;

    @Transient
    @EqualsAndHashCode.Exclude
    private boolean newEntity;

    public SystemUserEntity(String uuid, String username, String firstname, String lastname, boolean newEntity) {
        this.uuid = uuid;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.newEntity = newEntity;
    }

    public SystemUserEntity(SystemUser systemUser) {
        this.uuid = systemUser.getUuid();
        this.newEntity = true;
        update(systemUser);
    }

    public SystemUserEntity(SystemUserEntity user) {
        this.uuid = user.uuid;
        this.username = user.username;
        this.firstname = user.firstname;
        this.lastname = user.lastname;
        this.newEntity = user.newEntity;
    }

    @Override
    public String getId() {
        return uuid;
    }

    @Override
    public boolean isNew() {
        return newEntity;
    }

    @Override
    public SystemUserEntity update(SystemUser dto) {
        this.username = dto.getUsername();
        this.firstname = dto.getFirstname();
        this.lastname = dto.getLastname();
        return this;
    }

    @Override
    public SystemUser getDefaultDto() {
        return SystemUser.builder()
                .uuid(uuid)
                .username(username)
                .firstname(firstname)
                .lastname(lastname)
                .build();
    }
}
