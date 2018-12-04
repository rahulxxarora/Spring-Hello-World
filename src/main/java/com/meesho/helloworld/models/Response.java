package com.meesho.helloworld.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("email")
    private String email;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status_code")
    private Integer statusCode;

    public Response(String email, String message, Integer code){
        this.email = email;
        this.message = message;
        this.statusCode = code;
    }
}