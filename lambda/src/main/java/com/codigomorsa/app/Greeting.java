package com.codigomorsa.app;

import com.codigomorsa.app.services.DaggerVetFactory;
import com.codigomorsa.app.services.Vet;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Map;

final class Payload {
    String version;
    Params queryStringParameters;

    final static class Params {
        String dogName;
        String dogAge;
    }
}

public class Greeting {
    private static final Gson gson = new Gson();
    private final Vet vet;

    public Greeting() {
        this(DaggerVetFactory.create().init());
    }

    Greeting(Vet vet) {
        this.vet = vet;
    }

    public Map<String, Object> onEvent(Map<String, Object> request) {
        Payload.Params params = getParams(request);

        if (params == null || params.dogName == null) {
            return Map.of(
                    "statusCode", 400,
                    "body", "Bad input: objectKey missing");
        }

        System.out.println(vet.diagnose(params.dogName));

        return Map.of(
                "statusCode", 200,
                "body", "The name of the dog is " + params.dogName);
    }

    private Payload.Params getParams(Map<String, Object> request) {
        JsonElement jsonElement = gson.toJsonTree(request);
        return gson.fromJson(jsonElement, Payload.class).queryStringParameters;
    }
}
