package pro.vaidas.notebookserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.vaidas.notebookserver.repository.impl.NoteDAO;

import java.util.Optional;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<NoteDAO, UUID> {
    Page<NoteDAO> findAllByTitleIsLikeIgnoreCase(String title, Pageable pageable);
    Page<NoteDAO> findAllByContentIsLikeIgnoreCase(String content, Pageable pageable);
    Page<NoteDAO> findAllByUserUUID(String userUUID, Pageable pageable);

    Optional<NoteDAO> findByContent(String content);
    // todo update to isLikeIgnoreCase
}
