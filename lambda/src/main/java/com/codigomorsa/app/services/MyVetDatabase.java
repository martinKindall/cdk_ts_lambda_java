package com.codigomorsa.app.services;

import javax.inject.Inject;

public class MyVetDatabase implements VetDatabase {

    @Inject
    public MyVetDatabase() {}

    @Override
    public void saveDiagnose(String diagnose) {
        System.out.println("Diagnose was saved in DynamoDB!");
    }
}
