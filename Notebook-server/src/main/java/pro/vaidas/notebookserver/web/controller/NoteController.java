package pro.vaidas.notebookserver.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import pro.vaidas.notebookserver.business.service.NoteService;
import pro.vaidas.notebookserver.model.Note;

import java.util.UUID;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NoteService service;

    @GetMapping
    public String getAllNotes(Model model){
        model.addAttribute("notes", service.getAllNotes());
        return "notes";
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
    public RedirectView createNote(Model model, Note note){
        service.addNote(note);
        return new RedirectView("/notes");
    }

    @PostMapping("/{id}")
    public RedirectView updateNote(Model model, @PathVariable UUID id, Note note){
        service.updateNote(id, note);
        return new RedirectView("/notes");
    }

    @DeleteMapping("/{id}")
    public String deleteNote(Model model, @PathVariable UUID id){
        service.deleteNote(id);
        return "redirect:/";
    }

}
