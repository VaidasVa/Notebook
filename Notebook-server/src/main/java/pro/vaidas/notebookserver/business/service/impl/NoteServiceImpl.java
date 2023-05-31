package pro.vaidas.notebookserver.business.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import pro.vaidas.notebookserver.business.mappers.NoteMapper;
import pro.vaidas.notebookserver.business.repository.NoteRepository;
import pro.vaidas.notebookserver.business.repository.impl.NoteDAO;
import pro.vaidas.notebookserver.business.service.NoteService;
import pro.vaidas.notebookserver.model.Note;

import java.time.LocalDateTime;
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

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_PAGE_SIZE = 20;

    @Override
    public Page<Note> getAllNotes(String title, String content, Integer pageNumber, Integer pageSize) {

        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);

        Page<NoteDAO> notePage;

        if (StringUtils.hasText(title) && !StringUtils.hasText(content)) {
            notePage = listNotesByTitle(title, pageRequest);
        } else if (StringUtils.hasText(content) && !StringUtils.hasText(title)) {
            notePage = listNotesByContent(content, pageRequest);
        } else {
            notePage = repository.findAll(pageRequest);
        }

        System.out.println("Total pages: " + notePage.getTotalPages());
        System.out.println("Total notes: " + notePage.getTotalElements());
        return notePage.map(mapper::noteDAOToNote);
    }

    public Page<NoteDAO> listNotesByTitle(String title, Pageable pageable) {
        return repository.findAllByTitleIsLikeIgnoreCase("%" + title + "%", pageable);
    }

    public Page<NoteDAO> listNotesByContent(String content, Pageable pageable) {
        return repository.findAllByContentIsLikeIgnoreCase("%" + content + "%", pageable);
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

    @Override
    public Note getNoteById(UUID id) {
        return repository.findById(id)
                .map(mapper::noteDAOToNote)
                .orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND));
    }

    @Override
    public void addNote(Note note){
        mapper.noteDAOToNote(repository
                .save(mapper.noteToNoteDAO(note)));
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
    public void deleteNote(UUID id){
        repository.deleteById(id);
    }

}
