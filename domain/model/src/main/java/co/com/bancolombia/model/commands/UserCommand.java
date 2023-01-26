package co.com.bancolombia.model.commands;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
public class UserCommand<T> {
    private final String name; // nombre del comando
    private final String commandId;

    private final String appName; // routingkey para el comando, comando se envía a una cola con el nombre de la app
    private final T data;
}
