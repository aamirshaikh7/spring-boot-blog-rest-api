package com.demo.blogrestapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@ApiModel(description = "ErrorDetails Model information")
@AllArgsConstructor
@Getter
public class ErrorDetails {
    @ApiModelProperty("ErrorDetails timestamp")
    private Date timestamp;

    @ApiModelProperty("ErrorDetails message")
    private String message;

    @ApiModelProperty("ErrorDetails path")
    private String path;
}
