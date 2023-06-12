package pro.vaidas.notebookserver.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import pro.vaidas.notebookserver.service.NoteService;
import pro.vaidas.notebookserver.model.KafkaMessage;
import pro.vaidas.notebookserver.model.Note;

import java.util.UUID;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteService service;
    private final KafkaTemplate<String, KafkaMessage> kafka;

    private String TOPIC = "NotebookServerTopic";

    public NoteController(NoteService service, KafkaTemplate<String, KafkaMessage> kafka) {
        this.service = service;
        this.kafka = kafka;
    }

    @GetMapping
    public String getAllNotes(Model model,
        @RequestParam(value = "title", required = false) String title,
        @RequestParam(value = "content", required = false) String content,
        @RequestParam(required = false) Integer pageNumber,
        @RequestParam(required = false) Integer pageSize){
        model.addAttribute("notes", service.getAllNotes(content, pageNumber, pageSize));
        return "notes";
    }

    @GetMapping("/user/{userUUID}")
    public String getNotesByUserUUID(Model model,
            @PathVariable(value = "userUUID") String userUUID,
            @RequestParam(required = false) Integer pageNumber,
            @RequestParam(required = false) Integer pageSize) {
        model.addAttribute("notes", service.getNotesByUserId(userUUID, pageNumber, pageSize));
        return "notesByUser";
    }

    @GetMapping("/{id}")
    public String getNoteById(Model model, @PathVariable("id") UUID id){
        Note note = service.getNoteById(id);
        model.addAttribute("note", note);
        return "note";
    }

    @GetMapping("/new")
    public String newNote(Model model){
        model.addAttribute("note", new Note());
        return "newNote";
    }

    @PostMapping
    public RedirectView createNote(Note note){
        service.addNote(note);
        kafka.send(TOPIC, service.makeKafkaNote(note, "newNote"));
        return new RedirectView("/notes");
    }

    @PostMapping("/{id}")
    public RedirectView updateNote(@PathVariable UUID id, Note note){
        service.updateNote(id, note);
        return new RedirectView("/notes");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteNote(@PathVariable UUID id){
        service.deleteNote(id);
        return new RedirectView("/notes");
    }

}
