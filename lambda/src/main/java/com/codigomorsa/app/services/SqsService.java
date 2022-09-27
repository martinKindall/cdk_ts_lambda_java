package com.codigomorsa.app.services;


import javax.inject.Inject;

public class SqsService implements QueueSender {

    @Inject
    public SqsService() {}

    @Override
    public void send(String payload) {

    }
}
