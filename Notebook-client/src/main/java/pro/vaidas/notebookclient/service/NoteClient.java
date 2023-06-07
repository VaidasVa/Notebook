package pro.vaidas.notebookclient.service;

import org.springframework.http.HttpStatus;
import pro.vaidas.notebookclient.model.Note;
import pro.vaidas.notebookclient.wrapper.PageableResponse;

import java.util.UUID;

public interface NoteClient {

    PageableResponse<Note> getNotes(String title, String content, Integer pageNumber, Integer pageSize);

    Note getNoteById(UUID id);

    HttpStatus addNote(Note note);

    void updateNote(UUID id, Note note);

    void deleteNote(UUID id);

}
