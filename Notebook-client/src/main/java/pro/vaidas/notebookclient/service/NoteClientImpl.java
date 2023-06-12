package pro.vaidas.notebookclient.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pro.vaidas.notebookclient.model.Note;
import pro.vaidas.notebookclient.wrapper.PageableResponse;

import java.util.UUID;

@Service
public class NoteClientImpl implements NoteClient {

    private final RestTemplate restTemplate;

    private static final String NOTES_PATH = "/api/v1/notes";
    private static final String NOTES_PATH_BY_ID = "/api/v1/notes/{id}";

    public NoteClientImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public PageableResponse<Note> getNotes(String title, String content, Integer pageNumber, Integer pageSize) {

        String size, number, text;

        if (pageSize != null){ size = "pageSize="+pageSize;}
        else {size = "";}
        if (pageNumber != null) {number = "pageNumber="+pageNumber;}
        else {number = "";}
        if (content != null) { text = "content=" + content;}
        else {text = "";}

        String url = NOTES_PATH + "?" + size + "&" + number + "&" + text;

        ResponseEntity<PageableResponse<Note>> response =
                restTemplate.exchange((url), HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {                        });
        return response.getBody();
    }

    @Override
    @LoadBalanced
    public Note getNoteById(UUID id) {
//        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("","");
        ResponseEntity<Note> response = restTemplate.getForEntity(NOTES_PATH_BY_ID, Note.class, id);
        return response.getBody();
    }

    @Override
    public HttpStatus addNote(Note note) {
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(NOTES_PATH, note, HttpStatus.class);
        return response.getBody();
    }

    @Override
    public void updateNote(UUID id, Note note) {
        restTemplate.put(NOTES_PATH_BY_ID, note, id);
    }

    @Override
    public void deleteNote(UUID id) {
        restTemplate.delete(NOTES_PATH_BY_ID, id);
    }
}
