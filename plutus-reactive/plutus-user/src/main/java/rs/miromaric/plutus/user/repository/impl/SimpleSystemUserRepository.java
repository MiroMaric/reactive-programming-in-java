package rs.miromaric.plutus.user.repository.impl;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.entity.SystemUserEntity;
import rs.miromaric.plutus.user.repository.SystemUserRepository;

import java.util.function.BiFunction;

@Repository
@Slf4j
@RequiredArgsConstructor
public class SimpleSystemUserRepository implements SystemUserRepository {

    private final DatabaseClient databaseClient;

    @Override
    public Mono<SystemUserEntity> save(SystemUserEntity user) {
        return databaseClient.inConnection(connection -> {
                    String query = "INSERT INTO SYSTEM_USER(uuid, username, first_name, last_name) VALUES ($1, $2, $3, $4)";
                    return Mono.from(connection.createStatement(query)
                            .bind("$1", user.getUuid())
                            .bind("$2", user.getUsername())
                            .bind("$3", user.getFirstname())
                            .bind("$4", user.getLastname())
                            .execute())
                            .log();
        }).flatMap(u -> findById(user.getUuid()));
    }

    @Override
    public Mono<SystemUserEntity> findById(String uuid) {
        return databaseClient.inConnectionMany(connection ->
                Flux.from(connection.createStatement("SELECT * FROM SYSTEM_USER WHERE uuid = $1")
                        .bind("$1", uuid)
                        .execute()))
                .flatMap(result -> result.map(mapToEntity()))
                .single()
                .log();
    }

    @Override
    public Flux<SystemUserEntity> findAll() {
        return databaseClient.inConnectionMany(connection ->
                Flux.from(connection.createStatement("SELECT * FROM SYSTEM_USER")
                        .execute())
                        .flatMap(result -> result.map(mapToEntity())))
                .log();
    }

    @Override
    public Mono<SystemUserEntity> update(SystemUserEntity user) {
        return databaseClient.inConnectionMany(connection ->
                Flux.from(connection.createStatement("UPDATE SYSTEM_USER SET username=$1, first_name=$2, last_name=$3")
                .bind("$1", user.getUsername())
                .bind("$2", user.getFirstname())
                .bind("$3", user.getLastname())
                .execute())
                .map(result -> user)
                .log()
        ).single();
    }

    @Override
    public Mono<Void> deleteById(String uuid) {
        return databaseClient.inConnectionMany(connection ->
                Flux.from(connection.createStatement("DELETE FROM SYSTEM_USER WHERE UUID = $1")
                        .bind("$1", uuid).execute()))
                .then()
                .log();
    }

    private BiFunction<Row, RowMetadata, SystemUserEntity> mapToEntity() {
        return (row, rowMetadata) -> {
            SystemUserEntity user = new SystemUserEntity(
                    row.get("uuid", String.class),
                    row.get("username", String.class),
                    row.get("first_name", String.class),
                    row.get("last_name", String.class),
                    false);
            log.warn(user.toString());
            return user;
        };
    }
}
