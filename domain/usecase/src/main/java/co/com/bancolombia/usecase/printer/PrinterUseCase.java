package co.com.bancolombia.usecase.printer;

import co.com.bancolombia.model.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.core.publisher.Mono;
@Log
@RequiredArgsConstructor
public class PrinterUseCase {

    public Mono<Void> logIncomingData(User user){
        return Mono.just(true)
                .flatMap(d -> {
                    log.info("datos de entrada >>>>>>>>>>>>>>" + user.toString());
                    return Mono.empty();
                } );

    }
}
