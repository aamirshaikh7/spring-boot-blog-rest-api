package com.demo.blogrestapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@ApiModel(description = "PostResponse Model information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
    @ApiModelProperty("PostResponse content")
    private List<PostDto> content;

    @ApiModelProperty("PostResponse pageNo")
    private int pageNo;

    @ApiModelProperty("PostResponse pageSize")
    private int pageSize;

    @ApiModelProperty("PostResponse totalPages")
    private int totalPages;

    @ApiModelProperty("PostResponse totalElements")
    private long totalElements;

    @ApiModelProperty("PostResponse isLast")
    private boolean isLast;
}
