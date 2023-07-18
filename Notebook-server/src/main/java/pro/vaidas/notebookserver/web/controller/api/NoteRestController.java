package pro.vaidas.notebookserver.web.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.vaidas.notebookserver.service.NoteService;
import pro.vaidas.notebookserver.model.KafkaMessage;
import pro.vaidas.notebookserver.model.Note;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteRestController {

    private final NoteService service;

    private final KafkaTemplate<String, KafkaMessage> kafka;

    private static final String TOPIC = "NotebookServerTopic";

    public NoteRestController(NoteService service, KafkaTemplate<String, KafkaMessage> kafka) {
        this.service = service;
        this.kafka = kafka;
    }

    @GetMapping
    public Page<Note> getAllNotes(
            @RequestParam(required = false) String content,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
            return service.getAllNotes(content, pageNumber, pageSize);
    }

    @GetMapping("/user/{userUUID}")
    public Page<Note> getNotesByUserUUID(
            @PathVariable(value = "userUUID") String userUUID,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
        return service.getNotesByUserId(userUUID, pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public Note getNoteById(@PathVariable UUID id) {
        return service.getNoteById(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> postNote(@RequestBody Note note) {
        service.addNote(note);
//        kafka.send(TOPIC, service.makeKafkaNote(note, "newNote"));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> putNote(@PathVariable UUID id, @RequestBody Note note) {
        service.updateNote(id, note);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteNote(@PathVariable UUID id) {
        service.deleteNote(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/access-denied")
    public String denied(){
        return "I see what you're doing, but no. Access denied.";
    }

}
