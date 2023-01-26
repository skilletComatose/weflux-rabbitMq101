package co.com.bancolombia.events;

import co.com.bancolombia.model.events.CustomDomainEvent;
import co.com.bancolombia.model.events.gateways.CustomDomainEventGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.api.domain.DomainEvent;
import org.reactivecommons.api.domain.DomainEventBus;
import org.reactivecommons.async.impl.config.annotations.EnableDomainEventBus;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Level;

import static reactor.core.publisher.Mono.from;

@Log
@RequiredArgsConstructor
@EnableDomainEventBus
public class UserReactiveEventsGateway implements CustomDomainEventGateway {

    private final DomainEventBus domainEventBus;


    @Override
    public <T> Mono<Void> emit(CustomDomainEvent<T> event) {
        log.log(Level.INFO, "Sending domain event: {0}: {1}", new String[]{event.getName(), event.toString()});
        return from(domainEventBus.emit(new DomainEvent<>(event.getName(), UUID.randomUUID().toString(), event.getData())));
    }
}
