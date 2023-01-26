package co.com.bancolombia.model.events.gateways;

import co.com.bancolombia.model.events.CustomDomainEvent;
import reactor.core.publisher.Mono;

public interface CustomDomainEventGateway {
    <T> Mono<Void> emit(CustomDomainEvent<T> event);
}
