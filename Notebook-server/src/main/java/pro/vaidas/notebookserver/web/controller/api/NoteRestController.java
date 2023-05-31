package pro.vaidas.notebookserver.web.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.notebookserver.business.service.NoteService;
import pro.vaidas.notebookserver.model.Note;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteRestController {

    private final NoteService service;

    @GetMapping
    public Page<Note> getAllNotes(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
            return service.getAllNotes(title, content, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public Note getNoteById(Model model, @PathVariable UUID id) {
        Note note = service.getNoteById(id);
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
