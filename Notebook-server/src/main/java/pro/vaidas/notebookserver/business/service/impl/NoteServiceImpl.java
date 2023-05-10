package pro.vaidas.notebookserver.business.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pro.vaidas.notebookserver.business.mappers.NoteMapper;
import pro.vaidas.notebookserver.business.repository.NoteRepository;
import pro.vaidas.notebookserver.business.service.NoteService;
import pro.vaidas.notebookserver.model.Note;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    private final NoteMapper mapper;

    @Override
    public List<Note> getAllNotes() {
        return repository.findAll()
                .stream()
                .map(mapper::noteDAOToNote)
                .sorted(Comparator.comparing(Note::getUpdated).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Note getNoteById(UUID id) {
        return repository.findById(id)
                .map(mapper::noteDAOToNote)
                .orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    public Note addNote(Note note){
        return mapper.noteDAOToNote(repository
                .save(mapper.noteToNoteDAO(note)));
    }

    @Override
    public Optional<Note> updateNote(UUID id, Note note) {

        AtomicReference<Optional<Note>> atomicReference = new AtomicReference<>();

        repository.findById(id).ifPresentOrElse(
                found -> {
                    if (note.getTitle().isEmpty()) {
                        throw new ResponseStatusException(BAD_REQUEST, "Note title cannot be empty. ");
                    } else {
                        found.setTitle(note.getTitle());
                        found.setContent(note.getContent());
                        found.setUpdated(LocalDateTime.now());
                        atomicReference.set(Optional.of(
                                mapper.noteDAOToNote(repository.save(found))));
                    }
                },
                () -> {
                    throw new ResponseStatusException(NOT_FOUND,
                            "Note with this ID could not be found for update.");
                });
        return atomicReference.get();
    }

    @Override
    public void deleteNote(UUID id){
        repository.deleteById(id);
    }

}
