package pro.vaidas.notebookserver.web.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.notebookserver.business.service.UserService;
import pro.vaidas.notebookserver.model.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserRestController {
    private final UserService service;

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity postUser(@RequestBody User user){
        service.saveUser(user);
        return new ResponseEntity(HttpStatus.OK);
    }

}
