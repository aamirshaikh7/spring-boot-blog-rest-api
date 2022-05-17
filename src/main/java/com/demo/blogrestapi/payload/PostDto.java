package com.demo.blogrestapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@ApiModel(description = "Post Model information")
@Data
public class PostDto {
    @ApiModelProperty("post id")
    private long id;

    @ApiModelProperty("post title")
    @NotEmpty
    @Size(min = 2)
    private String title;

    @ApiModelProperty("post description")
    @NotEmpty
    @Size(min = 5)
    private  String description;

    @ApiModelProperty("post content")
    @NotEmpty
    private String content;

    @ApiModelProperty("post comments")
    private Set<CommentDto> comments;
}
