package com.demo.blogrestapi.payload;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "JwtAuth Model information")
public class JwtAuthDto {
    @ApiModelProperty("JwtAuth accessToken")
    private String accessToken;

    @ApiModelProperty("JwtAuth tokenType")
    private String tokenType = "Bearer";

    public JwtAuthDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
