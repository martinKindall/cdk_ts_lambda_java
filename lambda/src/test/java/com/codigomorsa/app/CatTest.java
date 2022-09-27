package com.codigomorsa.app;

import com.codigomorsa.app.services.DaggerTestVetFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class CatTest {

    @Test
    public void onEventTest() {
        var testFactory = DaggerTestVetFactory.builder().build();
        var testDb = testFactory.testDb();
        var cat = new Cat(testFactory);

        Assertions.assertEquals(0, testDb.getExecuted());

        cat.onEvent(Map.of("body", "{'name': 'felix', 'age': 5}"));

        Assertions.assertEquals(1, testDb.getExecuted());
    }
}
