package rs.miromaric.plutus.user.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SystemUser {
    private String uuid;
    private String username;
    private String firstname;
    private String lastname;
    //private boolean isActive;
}
