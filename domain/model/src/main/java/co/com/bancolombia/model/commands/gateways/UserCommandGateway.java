package co.com.bancolombia.model.commands.gateways;

import co.com.bancolombia.model.commands.UserCommand;

import reactor.core.publisher.Mono;

public interface UserCommandGateway {
    <T> Mono<Void> sendCommand(UserCommand<T> command);
}
