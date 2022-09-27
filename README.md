# CDK using Typescript, Lambdas using Java 11

I wanted to create a project where the CDK and the lambdas were
written on different languages. I chosed Java for Lambdas just because of personal taste, and also wanted to use a Dependency Injection system on lambdas
avoiding the use of Spring Boot.

Dagger 2 was used on this toy project, showing me the easy of use and the powerful tool it can be.

Unit tests were done using Junit 5 and Dagger 2 for mocking.

## Domains

There are 2 domains in this project:

- Vet (lambda folder)
- Shipping

Vet domain is just a collection of dummy routes that inject data through rest endpoints. 

Shipping domain is another "dummy" domain that for now is receiving data from __Vet__ through an SQS queue.

## Architecture

The final architecture should look like this:

![Image](/architecture.png "Architecture")

Just replace the "Orders" domain for Vet.
DynamoDB for now is just a dummy service, there's no database in place.
Now that we are using Dependency Injection that should be implemented in one single place.

Lambdas on the same domain share code following the [Fat Lambda pattern](https://github.com/cdk-patterns/serverless/blob/main/the-lambda-trilogy/README.md). 

This architecture would scale in terms of complexity better if we add an explicit orchestrator layer using Step Functions for example.

## IAM roles & policies

As we know, EC2 and Lambdas in Java follow the [Default Credential Provider Chain](https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/credentials.html).
That means, as long as they have a Role in the _Instance Profile_ with permissions for accessing AWS services, we don't need to care about credentials in code itself. We manage those permissions using the CDK. We grant the cat lambda permission to send to SQS.
And the receiver lambda receives permission for consuming sqs events from the Event Source creation.
