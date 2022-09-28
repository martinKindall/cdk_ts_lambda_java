package com.codigomorsa.app;

import com.codigomorsa.app.services.DaggerTestVetFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VetTest {

    @Test
    public void vetTest() {
        var testFactory = DaggerTestVetFactory.builder().build();
        var vet = testFactory.getVet();

        var testDb = testFactory.testDb();
        Assertions.assertEquals(0, testDb.getExecuted());

        Assertions.assertTrue(vet.diagnoseCat("felix", 5).contains("perfect"));
        Assertions.assertTrue(vet.diagnoseCat("figaro", 5).contains("figaro"));
        Assertions.assertTrue(vet.diagnoseCat("felix", 10).contains("full check"));
        Assertions.assertTrue(vet.diagnoseCat("tom", 10).contains("tom"));

        Assertions.assertEquals(4, testDb.getExecuted());
    }
}
