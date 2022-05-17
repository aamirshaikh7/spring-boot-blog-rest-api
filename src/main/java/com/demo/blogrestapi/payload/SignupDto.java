package com.demo.blogrestapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Signup Model information")
@Data
public class SignupDto {
    @ApiModelProperty("user name")
    private String name;

    @ApiModelProperty("user username")
    private String username;

    @ApiModelProperty("user email")
    private String email;

    @ApiModelProperty("user password")
    private String password;
}
