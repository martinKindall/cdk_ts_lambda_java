package com.codigomorsa.app.services;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TestVetDatabase implements VetDatabase {

    public int executed;

    @Inject
    public TestVetDatabase() {
        executed = 0;
    }

    @Override
    public void saveDiagnose(String diagnose) {
        executed += 1;
        System.out.println("Stubbed database call");
    }

    public int getExecuted() {
        return executed;
    }
}
