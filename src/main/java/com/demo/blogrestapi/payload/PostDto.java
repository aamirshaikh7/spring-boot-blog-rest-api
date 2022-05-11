package com.demo.blogrestapi.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class PostDto {
    private long id;

    @NotEmpty
    @Size(min = 2, message = "Title must have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 5, message = "Description must have at least 5 characters")
    private  String description;

    @NotEmpty
    private String content;

    private Set<CommentDto> comments;
}
