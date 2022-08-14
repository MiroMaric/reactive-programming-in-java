package rs.miromaric.plutus.payment.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PaymentApiEndpointConfiguration {

    @Bean
    @SuppressWarnings({"NullableProblems"})
    RouterFunction<ServerResponse> paymentEndpoints(PaymentApiHandler handler) {
        return RouterFunctions.route()
                .nest(RequestPredicates.path("/fn/api/v1/payment"), builder -> {
                    builder.nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), nestedBuilder -> {
                        nestedBuilder
                                .POST("/payIn", handler::payIn)
                                .POST("/payOut", handler::payOut);
                    });
                }).build();
    }

}
