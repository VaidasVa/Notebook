package pro.vaidas.authserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.authserver.model.User;
import pro.vaidas.authserver.service.UserFromDbService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/retrieve")
public class UserDataExchangeController {

    private final UserFromDbService service;

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("You're logged in");
    }

    @GetMapping(value = "/{email}")
    public User getUserWithUserClass(
            @PathVariable String email) {
        return service.getUserDetailsFromRemoteDB(email);
    }
}
