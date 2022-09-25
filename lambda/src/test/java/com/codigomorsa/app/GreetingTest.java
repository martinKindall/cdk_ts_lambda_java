package com.codigomorsa.app;

import com.codigomorsa.app.services.DaggerTestVetFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class GreetingTest {

    @Test
    public void greetingTest() {
        var testFactory = DaggerTestVetFactory.builder().build();
        var testDb = testFactory.testDb();

        Assertions.assertEquals(0, testDb.getExecuted());

        var greeting = new Greeting(testFactory.init());

        greeting.onEvent(Map.of("queryStringParameters", Map.of("dogName", "thonka")));

        Assertions.assertEquals(1, testDb.getExecuted());
    }
}
