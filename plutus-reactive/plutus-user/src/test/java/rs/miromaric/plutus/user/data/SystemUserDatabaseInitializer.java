package rs.miromaric.plutus.user.data;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperator;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.repository.SystemUserRepository;

import java.io.InputStreamReader;
import java.io.Reader;

@Component
public class SystemUserDatabaseInitializer {

    private final TransactionalOperator transactionalOperator;
    private final DatabaseClient client;
    private final String sql;
    private final SystemUserRepository repository;

    SystemUserDatabaseInitializer(@Value("classpath:/schema.sql") Resource resource,
                                  DatabaseClient client,
                                  @Qualifier("templateSystemUserRepository") SystemUserRepository repository,
                                  TransactionalOperator operator) throws Exception {

        this.client = client;
        this.repository = repository;
        this.transactionalOperator = operator;

        try (Reader in = new InputStreamReader(resource.getInputStream())) {
            this.sql = FileCopyUtils.copyToString(in);
        }
    }

    public Publisher<Void> resetTables() {
        Mono<Void> createSchema = client.sql(this.sql).then().onErrorResume(throwable -> Mono.empty());
        Flux<Void> findAndDelete = repository.findAll()
                .flatMap(user -> repository.deleteById(user.getUuid()));

        return createSchema.thenEmpty(this.transactionalOperator.execute(status -> findAndDelete));
    }

    public Publisher<Void> clearTables() {
        return repository.findAll().flatMap(user -> repository.deleteById(user.getUuid()));
    }

}
