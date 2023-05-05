package pro.vaidas.notebookclient.service;

import org.springframework.http.HttpStatus;
import pro.vaidas.notebookclient.model.Note;

import java.util.List;
import java.util.UUID;

public interface NoteClient {

    List<Note> getNotes();

    Note getNoteById(UUID id);

    HttpStatus addNote(Note note);

    void updateNote(UUID id, Note note);

    void deleteNote(UUID id);

}
