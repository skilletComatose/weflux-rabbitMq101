package co.com.bancolombia.events.config;

import co.com.bancolombia.model.user.User;
import co.com.bancolombia.usecase.printer.PrinterUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.reactivecommons.async.api.HandlerRegistry;
import org.reactivecommons.async.impl.config.annotations.EnableMessageListeners;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableMessageListeners
@RequiredArgsConstructor
@Log
public class DirectMessageConfig {

    @Value("${spring.user-command.find-by}")
    private  String USER_COMMAND;
    private final PrinterUseCase printerUseCase;
    @Bean
     public HandlerRegistry directMessages(){
         return HandlerRegistry.register()
                 .listenEvent("user.save", event ->  printerUseCase.logIncomingData(event.getData()), User.class)
                 .listenEvent("user.save-2", event ->  printerUseCase.logIncomingData(event.getData()), User.class)
                 .handleCommand(USER_COMMAND, event -> printerUseCase.logIncomingData(event.getData()), User.class);

     }
}
