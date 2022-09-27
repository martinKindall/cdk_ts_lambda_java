package com.codigomorsa.app.services;

import javax.inject.Inject;

public class Vet {
    private VetDatabase database;

    @Inject
    public Vet(VetDatabase database) {
        this.database = database;
    }

    public String diagnose(String dog) {
        String diagnose = dog + " is healthy :)";
        database.saveDiagnose(diagnose);
        return diagnose;
    }

    public String diagnoseCat(String cat, int age) {
        String diagnose;
        if (age >= 10) {
            diagnose = cat + " needs a full check.";
        } else {
            diagnose = cat + ", age " + age + ", is al right, the temperature is perfect.";
        }
        database.saveDiagnose(diagnose);
        return diagnose;
    }
}
