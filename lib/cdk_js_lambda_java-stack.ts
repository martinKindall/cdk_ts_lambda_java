import {Duration, Stack, StackProps} from 'aws-cdk-lib';
import * as lambda from 'aws-cdk-lib/aws-lambda';
import * as apigatewayv2 from '@aws-cdk/aws-apigatewayv2-alpha';
import {HttpLambdaIntegration} from '@aws-cdk/aws-apigatewayv2-integrations-alpha'
import { Construct } from 'constructs';

export class CdkJsLambdaJavaStack extends Stack {
  private dogLambda: lambda.Function;
  private catLambda: lambda.Function;

  constructor(scope: Construct, id: string, props?: StackProps) {
    super(scope, id, props);

    this.createLambdas();
    this.httpApis();
  }

  private createLambdas() {
    this.dogLambda = new lambda.Function(this, 'MyLambda', {
      runtime: lambda.Runtime.JAVA_11,
      handler: "com.codigomorsa.app.Dog::onEvent",
      code: lambda.Code.fromAsset("./lambda/build/libs/app-1.0-SNAPSHOT-all.jar"),
      memorySize: 256,
      timeout: Duration.seconds(10)
    });

    this.catLambda = new lambda.Function(this, 'CatLambda', {
      runtime: lambda.Runtime.JAVA_11,
      handler: "com.codigomorsa.app.Cat::onEvent",
      code: lambda.Code.fromAsset("./lambda/build/libs/app-1.0-SNAPSHOT-all.jar"),
      memorySize: 256,
      timeout: Duration.seconds(10)
    });
  }

  private httpApis() {
    const httpApi = new apigatewayv2.HttpApi(this, 'MyTestApi');

    const lambdaIntegration = new HttpLambdaIntegration(
        'LambdaIntegration',
        this.dogLambda
    );

    httpApi.addRoutes({
      path: '/dogs',
      methods: [ apigatewayv2.HttpMethod.GET ],
      integration: lambdaIntegration,
    });

    const catLambdaIntegration = new HttpLambdaIntegration(
        'CatLambdaIntegration',
        this.catLambda
    );

    httpApi.addRoutes({
      path: '/cats',
      methods: [ apigatewayv2.HttpMethod.POST ],
      integration: catLambdaIntegration,
    });
  }
}
