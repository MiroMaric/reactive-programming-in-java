package rs.miromaric.plutus.user.api;

import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;

@WebFluxTest
@Import({ SystemUserApiEndpointConfiguration.class, SystemUserApiHandler.class })
public class SystemUserControllerTest extends SystemUserApiTest {

    @Override
    protected String getUri() {
        return "/api/v1/users/";
    }

}
