package pro.vaidas.notebookserver.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pro.vaidas.notebookserver.business.repository.impl.NoteDAO;

import java.util.UUID;

public interface NoteRepository extends JpaRepository<NoteDAO, UUID> {
    Page<NoteDAO> findAllByTitleIsLikeIgnoreCase(String title, Pageable pageable);
    Page<NoteDAO> findAllByContentIsLikeIgnoreCase(String content, Pageable pageable);
}
