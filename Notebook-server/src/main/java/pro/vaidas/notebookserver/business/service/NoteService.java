package pro.vaidas.notebookserver.business.service;

import org.springframework.data.domain.Page;
import pro.vaidas.notebookserver.model.Note;

import java.util.UUID;

public interface NoteService {

    Page<Note> getAllNotes(String title, String content, Integer pageNumber, Integer pageSize);

    Note getNoteById(UUID id);

    void addNote(Note note);

    void updateNote(UUID id, Note note);

    void deleteNote(UUID id);

}
