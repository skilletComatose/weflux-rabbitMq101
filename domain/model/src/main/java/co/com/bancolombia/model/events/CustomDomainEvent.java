package co.com.bancolombia.model.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class CustomDomainEvent<T> {
    private final String name; //routingKey que enlaza la cola al exchange por defecto
    private final String eventId;
    private final T data;
}
