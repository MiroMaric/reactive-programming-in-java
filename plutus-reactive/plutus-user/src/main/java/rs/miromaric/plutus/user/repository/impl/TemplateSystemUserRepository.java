package rs.miromaric.plutus.user.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rs.miromaric.plutus.user.entity.SystemUserEntity;
import rs.miromaric.plutus.user.repository.SystemUserRepository;

@Slf4j
@Repository
public class TemplateSystemUserRepository implements SystemUserRepository {

    private final R2dbcEntityTemplate entityTemplate;

    public TemplateSystemUserRepository(DatabaseClient databaseClient) {
        entityTemplate = new R2dbcEntityTemplate(databaseClient, PostgresDialect.INSTANCE);
    }

    @Override
    public Mono<SystemUserEntity> save(SystemUserEntity user) {
        return entityTemplate.insert(SystemUserEntity.class).using(user);
    }

    @Override
    public Mono<SystemUserEntity> findById(String id) {
        return entityTemplate.selectOne(Query.query(Criteria.where("uuid").is(id)), SystemUserEntity.class);
    }

    @Override
    public Flux<SystemUserEntity> findAll() {
        return entityTemplate.select(SystemUserEntity.class).all();
    }

    @Override
    public Mono<SystemUserEntity> update(SystemUserEntity user) {
        return entityTemplate.update(user);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return entityTemplate.delete(Query.query(Criteria.where("uuid").is(id)), SystemUserEntity.class).then();
    }

}
