package rs.miromaric.plutus.wallet.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WalletApiEndpointConfiguration {

    @Bean
    @SuppressWarnings({"NullableProblems"})
    RouterFunction<ServerResponse> walletEndpoints(WalletApiHandler handler) {
        return RouterFunctions.route()
                .nest(RequestPredicates.path("/fn/api/v1/wallets"), builder ->
                        builder.nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), nestedBuilder ->
                                nestedBuilder
                                        .POST("", handler::save)
                                        .GET("/{id}", handler::getById)
                                        .PUT("/{id}", handler::update)
                                        .DELETE("/{id}", handler::delete)
                                        .PUT("/{id}/pay-in", handler::payIn)
                                        .PUT("/{id}/pay-out", handler::payOut))
                ).build();
    }

}
