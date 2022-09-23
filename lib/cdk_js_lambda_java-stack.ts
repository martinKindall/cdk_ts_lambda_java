import {Duration, Stack, StackProps} from 'aws-cdk-lib';
import * as lambda from 'aws-cdk-lib/aws-lambda';
import * as apigatewayv2 from '@aws-cdk/aws-apigatewayv2-alpha';
import {HttpLambdaIntegration} from '@aws-cdk/aws-apigatewayv2-integrations-alpha'
import { Construct } from 'constructs';

export class CdkJsLambdaJavaStack extends Stack {
  constructor(scope: Construct, id: string, props?: StackProps) {
    super(scope, id, props);

    const someLambda = new lambda.Function(this, 'MyLambda', {
      runtime: lambda.Runtime.JAVA_11,
      handler: "com.codigomorsa.app.Greeting::onEvent",
      code: lambda.Code.fromAsset("./lambda/build/libs/app-1.0-SNAPSHOT-all.jar"),
      memorySize: 256,
      timeout: Duration.seconds(10)
    });

    const httpApi = new apigatewayv2.HttpApi(this, 'MyTestApi');

    const lambdaIntegration = new HttpLambdaIntegration(
        'LambdaIntegration',
        someLambda
    );

    httpApi.addRoutes({
      path: '/dogs',
      methods: [ apigatewayv2.HttpMethod.GET ],
      integration: lambdaIntegration,
    });
  }
}
