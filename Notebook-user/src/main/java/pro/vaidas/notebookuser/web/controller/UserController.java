package pro.vaidas.notebookuser.web.controller;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.notebookuser.model.Mail;
import pro.vaidas.notebookuser.model.User;
import pro.vaidas.notebookuser.service.MailingService;
import pro.vaidas.notebookuser.service.UserService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private MailingService mailer;

    @GetMapping("/add")
    public void addUsers(){
        service.addUsers();
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> list = service.findAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable UUID id){
        Optional<User> user = service.getUserById(id);
        if (user.isPresent()){
        return new ResponseEntity<>(user, HttpStatus.OK);}
        else {return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);}
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable @NotEmpty String email) {
        Optional<User> result = service.findUserByEmail(email);
        if (result.isPresent()) {
            return new ResponseEntity<>((result), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/check/{email}")
    public ResponseEntity<String> doesUserExistByEmail(@PathVariable String email) {
        if (service.userExistsByEmail(email)) {
            return new ResponseEntity<>("User found", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<String> postUser(@RequestBody User user) throws IOException, URISyntaxException {
        if (service.userExistsByEmail(user.getEmail())) {
            return new ResponseEntity<>("This email is already registered", HttpStatus.BAD_REQUEST);
        }
        if (user.getName() == null) {
            return new ResponseEntity<>("Name is mandatory", HttpStatus.BAD_REQUEST);
        } else if (user.getEmail() == null) {
            return new ResponseEntity<>("Email is mandatory", HttpStatus.BAD_REQUEST);
        } else if (user.getPassword() == null) {
            return new ResponseEntity<>("Password is mandatory", HttpStatus.BAD_REQUEST);
        } else {
            String response = mailer.sendEmail(new Mail(user.getEmail(), "New user", "New user"));
            service.saveUser(user);
            return new ResponseEntity<>("User saved, " + response, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        service.deleteUser(id);
        return new ResponseEntity<>("Deleted.", HttpStatus.NO_CONTENT);
    }
}
