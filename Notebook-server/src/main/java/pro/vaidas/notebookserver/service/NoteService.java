package pro.vaidas.notebookserver.service;

import org.springframework.data.domain.Page;
import pro.vaidas.notebookserver.model.KafkaMessage;
import pro.vaidas.notebookserver.model.Note;

import java.util.UUID;

public interface NoteService {

    Page<Note> getAllNotes(String content, Integer pageNumber, Integer pageSize);

    Page<Note> getNotesByUserId(String userUUID, Integer pageNumber, Integer pageSize);

    Note getNoteById(UUID id);

    void addNote(Note note);

    void updateNote(UUID id, Note note);

    void deleteNote(UUID id);

    KafkaMessage makeKafkaNote(Note note, String event);

}
