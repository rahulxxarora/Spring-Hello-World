package com.meesho.helloworld.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    @JsonProperty("id")
    private long id;

    @JsonProperty("status_code")
    private Integer statusCode;

    public Response(long id, Integer code){
        this.id = id;
        this.statusCode = code;
    }
}