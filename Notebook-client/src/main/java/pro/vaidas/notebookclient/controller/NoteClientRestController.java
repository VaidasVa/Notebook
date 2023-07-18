package pro.vaidas.notebookclient.controller;

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
    public PageableResponse<Note> getNotes(
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            return service.getNotes(content, pageNumber, pageSize);
        } else return null;
    }


    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody Note getNoteById(
            @PathVariable("id") UUID id,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            return service.getNoteById(id);
        } else return null;
    }

    @PostMapping
    public ResponseEntity postNote(
            @RequestBody Note note,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            service.addNote(note);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else return null;
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity putNote(
            @PathVariable UUID id,
            @RequestBody Note note,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            service.updateNote(id, note);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return null;
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteNote(
            @PathVariable("id") UUID id,
            @RequestHeader("Authorization") String authorizationHeader) {
        if (authService.setSecurityContext(authorizationHeader)) {
            service.deleteNote(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return null;
    }
}