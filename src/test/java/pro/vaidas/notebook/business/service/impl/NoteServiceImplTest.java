package pro.vaidas.notebook.business.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import pro.vaidas.notebook.business.mappers.NoteMapper;
import pro.vaidas.notebook.business.repository.NoteRepository;
import pro.vaidas.notebook.business.repository.impl.NoteDAO;
import pro.vaidas.notebook.model.Note;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

    @InjectMocks
    private NoteServiceImpl noteService;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteMapper mapper;

    private Note note;
    private NoteDAO noteDAO;

    @Test
    void getAllNotes() {
//        when(noteRepository.findById()).thenReturn(noteDAO);
    }

    @Test
    void getNoteById() {
    }

    @Test
    void addNote() {
    }

    @Test
    void updateNote() {
    }

    @Test
    void deleteNote() {
    }
}