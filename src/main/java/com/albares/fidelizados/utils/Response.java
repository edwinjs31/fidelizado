package com.albares.fidelizados.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private Integer responseCode;
    private GenericData genericData;

    public Response() {
    }

    public Response(Integer responseCode) {
        this.responseCode = responseCode;
    }
    
    public Response(Integer responseCode, GenericData genericData) {
        this.responseCode = responseCode;
        this.genericData = genericData;
    }
  

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public GenericData getGenericData() {
        return genericData;
    }

    public void setGenericData(GenericData genericData) {
        this.genericData = genericData;
    }

}
