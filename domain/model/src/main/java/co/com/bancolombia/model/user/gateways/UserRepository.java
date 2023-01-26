package co.com.bancolombia.model.user.gateways;

import co.com.bancolombia.model.globalresponse.GlobalResponse;
import co.com.bancolombia.model.user.User;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserRepository {
    Mono<GlobalResponse<User>> saveUser(User user);
    Mono<Void>  delete(String id);

    Mono<GlobalResponse<User>> findUserById(String id);

}
