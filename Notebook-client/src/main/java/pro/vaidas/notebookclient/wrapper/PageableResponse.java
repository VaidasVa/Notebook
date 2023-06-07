package pro.vaidas.notebookclient.wrapper;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PageableResponse<T> extends PageImpl<T> {
//    private Boolean first;
//    private Boolean last;
//    private int totalPages;
//    private int numberOfElements;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PageableResponse(
            @JsonProperty("content") List<T> content,
            @JsonProperty("number") int number,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") int totalElements,
            @JsonProperty("last") boolean last,
            @JsonProperty("first") boolean first,
            @JsonProperty("totalPages") int totalPages,
            @JsonProperty("numberOfElements") int numberOfElements,
            @JsonProperty("pageable") JsonNode pageable,
            @JsonProperty("sort") JsonNode sort) {

        super(content, PageRequest.of(number, size), totalElements);
    }

    public PageableResponse(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PageableResponse(List<T> content) {
        super(content);
    }

    public PageableResponse() {
        super(new ArrayList<>());
    }

//        this.last = last;
//        this.first = first;
//        this.totalPages = totalPages;
//        this.numberOfElements = numberOfElements;
    }



