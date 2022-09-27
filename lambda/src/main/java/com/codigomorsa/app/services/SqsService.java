package com.codigomorsa.app.services;


import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

import javax.inject.Inject;

public class SqsService implements QueueSender {

    private final String queueUrl = System.getenv("QUEUE_URL");

    @Inject
    public SqsService() {}

    @Override
    public void send(String payload) {
        var sqsClient = SqsClient.builder()
            .region(Region.US_EAST_1)
            .build();

        try {
            SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(payload)
                    .delaySeconds(5)
                    .build();

            sqsClient.sendMessage(sendMsgRequest);

        } catch (SqsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
}
