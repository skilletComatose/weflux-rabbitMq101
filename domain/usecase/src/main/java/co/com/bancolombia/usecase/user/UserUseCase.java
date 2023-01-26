package co.com.bancolombia.usecase.user;

import co.com.bancolombia.model.commands.UserCommand;
import co.com.bancolombia.model.commands.gateways.UserCommandGateway;
import co.com.bancolombia.model.events.CustomDomainEvent;
import co.com.bancolombia.model.events.gateways.CustomDomainEventGateway;
import co.com.bancolombia.model.globalresponse.GlobalResponse;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
public class UserUseCase {
    private final UserRepository userRepository;
    private final CustomDomainEventGateway customEventsGateway;

    private  final UserCommandGateway userCommandGateway;
    private final String ROUTING_KEY;
    private final String APP_NAME;

    private final String USER_COMMAND_NAME;

    public
    Mono<GlobalResponse<User>> saveUser(User user){
        return this.userRepository.saveUser(user)
                .flatMap(user1 -> this.customEventsGateway.emit(CustomDomainEvent.builder()
                                    .data(user1)
                                    .eventId(UUID.randomUUID().toString())
                                    .name(ROUTING_KEY)
                                    .build())
                        .thenReturn(user1)
                );


    }

    public Mono<GlobalResponse<User>> findUserById(String id){
        return this.userRepository.findUserById(id)
                .flatMap( user -> Objects.isNull(user.getData())
                                    ? Mono.empty().thenReturn(user)
                                    : this.userCommandGateway.sendCommand(
                                                                UserCommand.builder()
                                                                        .name(USER_COMMAND_NAME)
                                                                        .commandId(UUID.randomUUID().toString())
                                                                        .data(user.getData())
                                                                        .appName(APP_NAME)
                                                                        .build() )

                                                                .thenReturn(user)
                );
    }
}
