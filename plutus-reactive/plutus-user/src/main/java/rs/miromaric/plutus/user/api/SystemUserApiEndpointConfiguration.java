package rs.miromaric.plutus.user.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SystemUserApiEndpointConfiguration {

    @Bean
    @SuppressWarnings({"NullableProblems"})
    RouterFunction<ServerResponse> systemUserEndpoints(SystemUserApiHandler handler) {
        return RouterFunctions.route()
                .nest(RequestPredicates.path("/fn/api/v1/users"), builder ->
                        builder.nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), nestedBuilder -> {
                            nestedBuilder.POST("", handler::save)
                                    .GET("/{id}", handler::getById)
                                    .PUT("/{id}", handler::update)
                                    .DELETE("/{id}", handler::delete);
                        })
                ).build();
    }

}
