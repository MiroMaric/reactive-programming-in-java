package rs.miromaric.plutus.user.data;

import rs.miromaric.plutus.user.model.ImmutableSystemUser;
import rs.miromaric.plutus.user.model.SystemUser;

public class UserTestData {

    public static final SystemUser MIRO = ImmutableSystemUser
            .builder()
            .uuid("104825c7-9e29-4638-8123-05ceaba2873c")
            .username("miro")
            .firstname("miro")
            .lastname("maric")
            .build();

    public static final SystemUser PERA = ImmutableSystemUser
            .builder()
            .uuid("7fc134b4-43a3-4f08-ac90-ff9541dc4c2b")
            .username("pero")
            .firstname("pero")
            .lastname("maric")
            .build();

}
