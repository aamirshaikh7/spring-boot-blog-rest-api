package com.demo.blogrestapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Data
public class PostDtoV2 {
    private long id;

    @NotEmpty
    @Size(min = 2)
    private String title;

    @NotEmpty
    @Size(min = 5)
    private  String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> comments;

    private List<String> tags;
}
