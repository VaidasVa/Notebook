package pro.vaidas.notebookclient.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.vaidas.notebookclient.model.Note;
import pro.vaidas.notebookclient.service.NoteClientImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteClientRestController {

    private final NoteClientImpl service;

    @GetMapping
    public List<Note> getNotes() {
        return service.getNotes();
    }

    @GetMapping(value = "/{id}", produces="application/json")
    public @ResponseBody Note getNoteById(@PathVariable("id") UUID id){
        return service.getNoteById(id);
    }

    @PostMapping
    public ResponseEntity postNote(@RequestBody Note note){
        service.addNote(note);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity putNote(@PathVariable UUID id, @RequestBody Note note){
        service.updateNote(id, note);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteNote(@PathVariable("id") UUID id){
        service.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
