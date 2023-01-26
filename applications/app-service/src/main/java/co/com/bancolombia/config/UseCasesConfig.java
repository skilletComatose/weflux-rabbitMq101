package co.com.bancolombia.config;

import co.com.bancolombia.model.commands.gateways.UserCommandGateway;
import co.com.bancolombia.model.events.gateways.CustomDomainEventGateway;
import co.com.bancolombia.model.user.gateways.UserRepository;
import co.com.bancolombia.usecase.printer.PrinterUseCase;
import co.com.bancolombia.usecase.user.UserUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UseCasesConfig {
        @Bean
        public UserUseCase userUseCase(
                UserRepository userRepository,
                CustomDomainEventGateway eventsGateway,
                UserCommandGateway userCommandGateway,
                @Value("${spring.user-command.routing-key}") String routingKey,
                @Value("${spring.application.name}") String appName,
                @Value("${spring.user-command.find-by}") String command

                ){

                return  new UserUseCase(
                                userRepository,
                                eventsGateway,
                                userCommandGateway,
                                routingKey,
                                appName,
                                command
                );
        }

        @Bean
        public PrinterUseCase printerUseCase(){
                return new PrinterUseCase();
        }

}
