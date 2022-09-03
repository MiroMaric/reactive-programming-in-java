package rs.miromaric.plutus.wallet.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import rs.miromaric.plutus.wallet.service.proxy.SystemUserService;

import static rs.miromaric.plutus.wallet.data.WalletTestData.MIRO;

@TestConfiguration
public class PlutusCommonTestConfig {

    @Bean("testObjectMapper")
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new GuavaModule());

        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        mapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, true);
        mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        return mapper;
    }

    @Bean
    SystemUserService systemUserService() {
        SystemUserService mockedService = Mockito.mock(SystemUserService.class);
        Mockito.when(mockedService.get(Mockito.anyString())).thenReturn(MIRO);
        return mockedService;
    }

}
