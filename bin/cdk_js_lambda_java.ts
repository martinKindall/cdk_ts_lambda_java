#!/usr/bin/env node
import * as cdk from 'aws-cdk-lib';
import { CdkJsLambdaJavaStack } from '../lib/cdk_js_lambda_java-stack';

const app = new cdk.App();
new CdkJsLambdaJavaStack(app, 'CdkJsLambdaJavaStack');
