package co.com.bancolombia.events;

import co.com.bancolombia.model.commands.UserCommand;
import co.com.bancolombia.model.commands.gateways.UserCommandGateway;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import org.reactivecommons.api.domain.Command;
import org.reactivecommons.async.api.DirectAsyncGateway;
import org.reactivecommons.async.impl.config.annotations.EnableDirectAsyncGateway;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Log
@AllArgsConstructor
@EnableDirectAsyncGateway
public class UserReactiveDirectAsyncGateway implements UserCommandGateway {



    private final DirectAsyncGateway gateway;


    @Override
    public <T> Mono<Void> sendCommand(UserCommand<T> command) {
        log.info("enviando mensaje ------>" + command.toString());
        return gateway.sendCommand(new Command<>(
                                        command.getName(),
                                        command.getCommandId(),
                                        command.getData())
                                   ,command.getAppName());
    }
}
