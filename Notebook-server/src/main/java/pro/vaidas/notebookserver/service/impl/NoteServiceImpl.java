package pro.vaidas.notebookserver.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import pro.vaidas.notebookserver.mappers.NoteMapper;
import pro.vaidas.notebookserver.repository.NoteRepository;
import pro.vaidas.notebookserver.repository.impl.NoteDAO;
import pro.vaidas.notebookserver.service.NoteService;
import pro.vaidas.notebookserver.model.KafkaMessage;
import pro.vaidas.notebookserver.model.Note;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;
    private final NoteMapper mapper;

//    private static final Logger logger = LogManager.getLogger(NoteServiceImpl.class);

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    @Override
    public Page<Note> getAllNotes(String content, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<NoteDAO> notePage;

        if (StringUtils.hasText(content)) {
            notePage = listNotesByContent(content, pageRequest);
        } else {
            notePage = repository.findAll(pageRequest);
        }

        System.out.println("Total pages: " + notePage.getTotalPages());
        System.out.println("Total notes: " + notePage.getTotalElements());
        return notePage.map(mapper::noteDAOToNote);
    }

    @Override
    public Page<Note> getNotesByUserId(String userUUID, Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Page<NoteDAO> userNotesPage = new PageImpl<>(new ArrayList<>());

        if (StringUtils.hasText(userUUID)) {
            userNotesPage = listNotesByUserUUID(userUUID, pageRequest);
            System.out.println("Total pages for USER: " + userNotesPage.getTotalPages());
            System.out.println("Total notes FOR USER: " + userNotesPage.getTotalElements());
            return userNotesPage.map(mapper::noteDAOToNote);
        } else { userNotesPage = repository.findAll(pageRequest);
        return userNotesPage.map(mapper::noteDAOToNote);}
    }

    @Override
    public Note getNoteById(UUID id) {
        return repository.findById(id)
                .map(mapper::noteDAOToNote)
                .orElseThrow(
                        () -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    public void addNote(Note note) {
        note.setUserUUID(UUID.randomUUID().toString());
        mapper.noteDAOToNote(repository
                .save(mapper.noteToNoteDAO(note)));
//        logger.info("New note created : " + note.getTitle() + " - on : " + LocalDateTime.now());
    }

    @Override
    public void updateNote(UUID id, Note note) {

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
        atomicReference.get();
    }

    @Override
    public void deleteNote(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public KafkaMessage makeKafkaNote(Note note, String event) {
        return KafkaMessage.builder()
                .name(note.getTitle())
                .email("notebookmailer@gmail.com")
                .eventType(event)
                .build();
    }


    public Page<NoteDAO> listNotesByContent(String content, Pageable pageable) {
        return repository.findAllByContentIsLikeIgnoreCase("%" + content + "%", pageable);
    }

    public Page<NoteDAO> listNotesByUserUUID(String userUUID, Pageable pageable) {
        return repository.findAllByUserUUID(userUUID, pageable);
    }

    public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0) {
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if (pageSize > 1000) {
                queryPageSize = 1000;
            } else {
                queryPageSize = pageSize;
            }
        }

        Sort sort = Sort.by(Sort.Order.desc("updated"));

        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }
}
