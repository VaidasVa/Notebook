package pro.vaidas.authserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.authserver.model.User;
import pro.vaidas.authserver.service.UserDetailsRetrievalService;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserRetrievalControlerForTESTING {

    private final UserDetailsRetrievalService service;

    public UserRetrievalControlerForTESTING(UserDetailsRetrievalService service) {
        this.service = service;
    }

    @GetMapping(value = "/{email}")
    public @ResponseBody User getUserByEmail(@PathVariable("email") String email){
        return service.getUserByEmail(email);
    }
}
