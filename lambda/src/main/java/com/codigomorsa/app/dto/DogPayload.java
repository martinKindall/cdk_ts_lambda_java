package com.codigomorsa.app.dto;

public class DogPayload {
    public DogPayload.Params queryStringParameters;

    public final static class Params {
        public String dogName;
        public String dogAge;
    }
}
