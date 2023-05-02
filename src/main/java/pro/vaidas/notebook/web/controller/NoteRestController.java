package pro.vaidas.notebook.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.notebook.business.service.NoteService;
import pro.vaidas.notebook.model.Note;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteRestController {

    private final NoteService service;

    @GetMapping
    public List<Note> getAllNotes() {
            return service.getAllNotes();
    }

    @GetMapping("/{id}")
    public Note getNoteById(Model model, @PathVariable UUID id) {
        Note note = service.getNoteById(id);
//        model.addAttribute("note", note);
        return service.getNoteById(id);
    }

    @PostMapping
    public ResponseEntity postNote(@RequestBody Note note) {
        service.addNote(note);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity putNote(@PathVariable UUID id, @RequestBody Note note) {
        service.updateNote(id, note);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteNote(@PathVariable UUID id) {
        service.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
