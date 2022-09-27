package com.codigomorsa.app;

import com.codigomorsa.app.dto.CatPayload;
import com.codigomorsa.app.services.DaggerVetFactory;
import com.codigomorsa.app.services.QueueSender;
import com.codigomorsa.app.services.Vet;
import com.codigomorsa.app.services.VetFactory;
import com.google.gson.Gson;

import java.util.Map;

public class Cat {
    private static final Gson gson = new Gson();
    private final Vet vet;
    private final QueueSender queueSender;

    public Cat() {
        this(DaggerVetFactory.create());
    }

    Cat(VetFactory vetFactory) {
        this.vet = vetFactory.init();
        this.queueSender = vetFactory.initQueue();
    }

    public Map<String, Object> onEvent(Map<String, Object> request) {
        CatPayload payload = getParams(request);

        if (payload.name == null || payload.name.equals("") || payload.age < 0)  {
            return Map.of("statusCode", 400);
        }

        System.out.println(vet.diagnoseCat(payload.name, payload.age));
        queueSender.send("Diagnosed cat " + payload.name + ". Ship receipt.");

        return Map.of("statusCode", 200);
    }

    private CatPayload getParams(Map<String, Object> request) {
        return gson.fromJson((String) request.get("body"), CatPayload.class);
    }
}
