package co.com.bancolombia.api;
import co.com.bancolombia.model.globalresponse.GlobalResponse;
import co.com.bancolombia.model.user.User;
import co.com.bancolombia.usecase.user.UserUseCase;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;



@RestController
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor

public class ApiRest {
     private final UserUseCase userUseCase;


    @GetMapping("")
    public ResponseEntity <Mono<GlobalResponse<User>>> findById(@RequestParam("id") String id){
        return ResponseEntity.ok(userUseCase.findUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity <Mono<GlobalResponse<User>>> saveUser(@RequestBody User user){
        return ResponseEntity.ok(userUseCase.saveUser(user));
    }
}
