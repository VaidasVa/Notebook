//package pro.vaidas.notebookserver.service.impl;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class NoteServiceImplTest {
//
//    @InjectMocks
//    private NoteServiceImpl service;
//    @Mock
//    private NoteRepository repository;
//    @Mock
//    private NoteMapper mapper;
//
//    private Note note;
//    private NoteDAO noteDAO;
//    private List<NoteDAO> noteDAOList;
//    private final UUID NOTE_ID = UUID.fromString("5525c1d0-d7f8-41c1-a11c-01ac53aa4b40");
//
//
//    @BeforeEach
//    void setup() {
//        note = noteObject();
//        noteDAO = noteDAOObject();
//        noteDAOList = noteDAOList(noteDAO);
//    }
//
//    @Test
//    @Disabled
//    void getAllNotes_Positive() {
//        when(repository.findAll()).thenReturn(noteDAOList);
//        when(mapper.noteDAOToNote(noteDAO)).thenReturn(note);
//
//        List<Note> notesList = service.getAllNotes();
//        Assertions.assertEquals(2, notesList.size());
//
//        verify(repository, times(1)).findAll();
//    }
//
//    @Test
//    void getAllNotes_Negative() {
//        List<NoteDAO> list = new ArrayList<>();
//
//        when(repository.findAll()).thenReturn(list);
//        List<Note> serviceList = service.getAllNotes();
//
//        assertTrue(serviceList.isEmpty());
//    }
//
//    @Test
//    void getNoteById_Positive() {
//        when(repository.findById(NOTE_ID)).thenReturn(Optional.of(noteDAO));
//        when(mapper.noteDAOToNote(noteDAO)).thenReturn(note);
//
//        Optional<Note> note = service.getNoteById(NOTE_ID);
//
//        note.ifPresent(result ->
//                assertEquals("Test note title", result.getTitle()));
//    }
//
//    @Test
//    @Disabled
//    void getNoteById_Negative() {
//        when(repository.findById(NOTE_ID)).thenReturn(Optional.empty());
//
//        Optional<Note> note = service.getNoteById(NOTE_ID);
//
//        assertTrue(note.isEmpty());
//    }
//
//    @Test
//    void addNote_Positive() {
//        when(repository.save(noteDAO)).thenReturn(noteDAO);
//        when(mapper.noteDAOToNote(noteDAO)).thenReturn(note);
//        when(mapper.noteToNoteDAO(note)).thenReturn(noteDAO);
//
//        Note savedNote = service.addNote(note);
//
//        assertThat(savedNote).isNotNull();
//        assertThat(savedNote.getId()).isNotNull();
//        verify(repository, times(1)).save(noteDAO);
//    }
//
//    @Test
//    void updateNote() {
//    }
//
//    @Test
//    void deleteNote() {
//        service.deleteNote(NOTE_ID);
//        verify(repository, times(1)).deleteById(NOTE_ID);
//    }
//
//    // TEST OBJECTS
//
//    private Note noteObject() {
//        return Note.builder()
//                .id(UUID.fromString("5525c1d0-d7f8-41c1-a11c-01ac53aa4b40"))
//                .title("Test note title")
//                .content("Test note content")
//                .created(LocalDateTime.of(2023, 5, 5, 12, 0, 0))
//                .updated(null)
//                .build();
//    }
//
//    private NoteDAO noteDAOObject() {
//        NoteDAO noteDAO = new NoteDAO();
//        noteDAO.setId(UUID.fromString("5525c1d0-d7f8-41c1-a11c-01ac53aa4b40"));
//        noteDAO.setTitle("Test note title");
//        noteDAO.setContent("Test note content");
//        noteDAO.setCreated(LocalDateTime.of(2023, 5, 5, 12, 0, 0));
//        noteDAO.setUpdated(null);
//        return noteDAO;
//    }
//
//    private List<NoteDAO> noteDAOList(NoteDAO noteDAO) {
//        List<NoteDAO> noteDAOS = new ArrayList<>();
//        noteDAOS.add(noteDAO);
//        noteDAOS.add(noteDAO);
//        noteDAOS.add(noteDAO);
//
//        return noteDAOS;
//    }
//
//}
