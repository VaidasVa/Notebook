package pro.vaidas.notebookserver.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;
import pro.vaidas.notebookserver.mappers.NoteMapper;
import pro.vaidas.notebookserver.model.Note;
import pro.vaidas.notebookserver.repository.NoteRepository;
import pro.vaidas.notebookserver.repository.impl.NoteDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @InjectMocks
    private NoteServiceImpl service;
    @Mock
    private NoteRepository repository;
    @Mock
    private NoteMapper mapper;

    private Note note;
    private NoteDAO noteDAO;
    private List<NoteDAO> noteDAOList;
    private final UUID NOTE_ID = UUID.fromString("5525c1d0-d7f8-41c1-a11c-01ac53aa4b40");


    @BeforeEach
    void setup() {
        note = noteObject();
        noteDAO = noteDAOObject();
        noteDAOList = noteDAOList(noteDAO);
    }


    @Test
    void getNoteById_Positive() {
        when(repository.findById(NOTE_ID)).thenReturn(Optional.of(noteDAO));
        when(mapper.noteDAOToNote(noteDAO)).thenReturn(note);

        Optional<Note> note = Optional.ofNullable(service.getNoteById(NOTE_ID));

        note.ifPresent(result ->
                assertEquals("Test note title", result.getTitle()));
    }

    @Test
    void getNoteById_Negative() {
        when(repository.findById(NOTE_ID)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.getNoteById(NOTE_ID));

        verify(repository, times(1)).findById(NOTE_ID);
    }

    @Test
    void addNote_Positive() {
        // not implemented, void method
    }

    @Test
    void updateNote() {
        // not implemented, void method
    }

    @Test
    void deleteNote() {
        service.deleteNote(NOTE_ID);
        verify(repository, times(1)).deleteById(NOTE_ID);
    }

    // TEST OBJECTS

    private Note noteObject() {
        return Note.builder()
                .id(UUID.fromString("5525c1d0-d7f8-41c1-a11c-01ac53aa4b40"))
                .title("Test note title")
                .content("Test note content")
                .created(LocalDateTime.of(2023, 5, 5, 12, 0, 0))
                .updated(null)
                .build();
    }

    private NoteDAO noteDAOObject() {
        NoteDAO noteDAO = new NoteDAO();
        noteDAO.setId(UUID.fromString("5525c1d0-d7f8-41c1-a11c-01ac53aa4b40"));
        noteDAO.setTitle("Test note title");
        noteDAO.setContent("Test note content");
        noteDAO.setCreated(LocalDateTime.of(2023, 5, 5, 12, 0, 0));
        noteDAO.setUpdated(null);
        return noteDAO;
    }

    private List<NoteDAO> noteDAOList(NoteDAO noteDAO) {
        List<NoteDAO> noteDAOS = new ArrayList<>();
        noteDAOS.add(noteDAO);
        noteDAOS.add(noteDAO);
        noteDAOS.add(noteDAO);

        return noteDAOS;
    }

}
