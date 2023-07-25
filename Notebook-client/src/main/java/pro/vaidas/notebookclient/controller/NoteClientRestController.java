package pro.vaidas.notebookclient.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.NotAuthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.notebookclient.model.AuthDetails;
import pro.vaidas.notebookclient.model.Note;
import pro.vaidas.notebookclient.service.Authentication;
import pro.vaidas.notebookclient.service.NoteClientImpl;
import pro.vaidas.notebookclient.wrapper.PageableResponse;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteClientRestController {

    private final NoteClientImpl service;

    private final Authentication authService;

    public NoteClientRestController(NoteClientImpl service, Authentication authService) {
        this.service = service;
        this.authService = authService;
    }

    @GetMapping
    @CircuitBreaker(name= "cBreaker-config", fallbackMethod = "fallbackMethodGetNotes")
    public PageableResponse<Note> getNotes(
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            return service.getNotes(content, pageNumber, pageSize);
        } else throw new NotAuthorizedException("Not authorized");
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody Note getNoteById(
            @PathVariable("id") UUID id,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            return service.getNoteById(id);
        } else throw new NotAuthorizedException("Not authorized");
    }

    @PostMapping
    @CircuitBreaker(name= "cBreaker-config", fallbackMethod = "fallbackMethodPostNote")
    public ResponseEntity<String> postNote(
            @RequestBody Note note,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            service.addNote(note);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else throw new NotAuthorizedException("Not authorized");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> putNote(
            @PathVariable UUID id,
            @RequestBody Note note,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            service.updateNote(id, note);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else throw new NotAuthorizedException("Not authorized");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteNote(
            @PathVariable("id") UUID id,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            service.deleteNote(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else throw new NotAuthorizedException("Not authorized");
    }

    public static final ResponseEntity<String> SERVICE_DOWN = new ResponseEntity<>("Remote service is unavailable",
            HttpStatus.SERVICE_UNAVAILABLE);

    public Object fallbackMethodGetNotes(
            String content, Integer pageNumber,
            Integer pageSize, String authorizationHeader,
            Exception ex){
        return SERVICE_DOWN;
    }

    public ResponseEntity<String> fallbackMethodPostNote(
            Note note, String s,
            Exception ex){
        return SERVICE_DOWN;
    }
}
