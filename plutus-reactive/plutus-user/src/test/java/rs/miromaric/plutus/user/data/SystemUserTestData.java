package rs.miromaric.plutus.user.data;

import rs.miromaric.plutus.user.model.SystemUser;

import java.util.List;

public class SystemUserTestData {

    public static final SystemUser MIKO = SystemUser.builder()
            .uuid("UUID1")
            .username("miko")
            .firstname("miro")
            .lastname("maric")
            .build();

    public static final SystemUser PERA = SystemUser.builder()
            .uuid("UUID2")
            .username("pera")
            .firstname("pera")
            .lastname("peric")
            .build();

    public static final List<SystemUser> USERS = List.of(MIKO, PERA);

}
