package rs.miromaric.plutus.paymentprovider.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PaymentProviderApiEndpointConfiguration {

    @Bean
    @SuppressWarnings("NullableProblems")
    RouterFunction<ServerResponse> paymentProviderApiEndpoints(PaymentProviderApiHandler handler) {
        return RouterFunctions.route()
                .nest(RequestPredicates.path("/fn/api/v1/payments"), builder ->
                        builder.nest(RequestPredicates.accept(MediaType.APPLICATION_JSON), nestedBuilder ->
                                nestedBuilder
                                        .POST("/debit", handler::debit)
                                        .POST("/credit", handler::credit)
                        )).build();
    }

}
