package co.com.bancolombia.mongo;

import co.com.bancolombia.model.globalresponse.GlobalResponse;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserRepository;
import co.com.bancolombia.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
public class MongoRepositoryAdapter extends AdapterOperations<User, UserData, String, MongoDBRepository> implements UserRepository {

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {

        super(repository, mapper, d -> mapper.mapBuilder(d,User.UserBuilder.class).build());
    }

    @Override
    public Mono<GlobalResponse<User>>saveUser(User user) {

        return this.save(user)
                .map(user1 ->GlobalResponse.<User>builder()
                        .code(201)
                        .data(user1)
                        .msg("ok")
                        .build() )
                .doOnError(err -> GlobalResponse.<User>builder()
                        .code(409)
                        .msg(err.toString())
                        .build());
    }

    @Override
    public Mono<Void> delete(String id) {
        return Mono.empty();
    }

    @Override
    public Mono<GlobalResponse<User>> findUserById(String id) {
        return this.findById(id)
                .map(user1 ->GlobalResponse.<User>builder()
                                .code(201)
                                .data(user1)
                                .msg("ok")
                                .build() )
                .switchIfEmpty(
                        Mono.just(
                                GlobalResponse.<User>builder()
                                        .code(404)
                                        .msg("not found")
                                        .build()
                        )

                )
        ;

    }
}
