package pro.vaidas.notebookserver.business.service;

import pro.vaidas.notebookserver.model.Note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteService {

    List<Note> getAllNotes();

    Note getNoteById(UUID id);

    Note addNote(Note note);

    Optional<Note> updateNote(UUID id, Note note);

    void deleteNote(UUID id);
}
