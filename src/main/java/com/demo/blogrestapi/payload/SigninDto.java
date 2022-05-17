package com.demo.blogrestapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Signin Model information")
@Data
public class SigninDto {
    @ApiModelProperty("user usernameOrEmail")
    private String usernameOrEmail;

    @ApiModelProperty("user password")
    private String password;
}
