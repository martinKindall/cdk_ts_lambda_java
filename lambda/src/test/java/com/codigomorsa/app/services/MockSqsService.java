package com.codigomorsa.app.services;

import javax.inject.Inject;

public class MockSqsService implements QueueSender {

    @Inject
    public MockSqsService() {}

    @Override
    public void send(String payload) {
        System.out.println("Stubbed message");
    }
}
