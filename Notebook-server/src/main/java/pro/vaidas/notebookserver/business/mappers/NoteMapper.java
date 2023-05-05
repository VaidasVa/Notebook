package pro.vaidas.notebookserver.business.mappers;

import org.mapstruct.Mapper;
import pro.vaidas.notebookserver.business.repository.impl.NoteDAO;
import pro.vaidas.notebookserver.model.Note;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDAO noteToNoteDAO(Note note);
    Note noteDAOToNote(NoteDAO noteDAO);

}