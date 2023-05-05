package pro.vaidas.notebookclient.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pro.vaidas.notebookclient.model.Note;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteClientImpl implements NoteClient {

    private final RestTemplateBuilder restTemplateBuilder;
    private static final String NOTES_PATH = "/api/v1/notes";
    private static final String NOTES_PATH_BY_ID = "/api/v1/notes/{id}";

    @Override
    public List<Note> getNotes() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(NOTES_PATH);

        ResponseEntity<List<Note>> response =
                restTemplate.exchange(
                        uriComponentsBuilder.toUriString(), HttpMethod.GET,null,
                        new ParameterizedTypeReference<List<Note>>(){});
        return response.getBody();
    }

    @Override
    public Note getNoteById(UUID id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<Note> response = restTemplate.getForEntity(NOTES_PATH_BY_ID, Note.class, id);
           return response.getBody();
    }

    @Override
    public HttpStatus addNote(Note note) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(NOTES_PATH, note, HttpStatus.class);
        System.out.println(response);
        return response.getBody();
    }

    @Override
    public void updateNote(UUID id, Note note) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(NOTES_PATH_BY_ID, note, id);
    }

    @Override
    public void deleteNote(UUID id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(NOTES_PATH_BY_ID, id);
    }
}
