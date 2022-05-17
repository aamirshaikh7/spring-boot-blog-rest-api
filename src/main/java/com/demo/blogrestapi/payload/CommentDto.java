package com.demo.blogrestapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "Comment Model information")
@Data
public class CommentDto {
    @ApiModelProperty("comment id")
    private long id;

    @ApiModelProperty("comment name")
    @NotEmpty
    private String name;

    @ApiModelProperty("comment email")
    @NotEmpty
    @Email
    private String email;

    @ApiModelProperty("comment body")
    @NotEmpty
    @Size(min = 5)
    private String body;
}
