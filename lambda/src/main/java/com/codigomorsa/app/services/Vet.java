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
}
