package pro.vaidas.notebookserver.mappers;

import org.mapstruct.Mapper;
import pro.vaidas.notebookserver.repository.impl.NoteDAO;
import pro.vaidas.notebookserver.model.Note;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteDAO noteToNoteDAO(Note note);
    Note noteDAOToNote(NoteDAO noteDAO);

}