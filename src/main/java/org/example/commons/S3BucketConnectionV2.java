package org.example.commons;
/**
//version 2 imports
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.async.AsyncResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.CompletionHandler;

public class S3BucketConnectionV2 {
    public static void main(String[] args) throws IOException {
        String bucketName = "lambdaartifact";
        String key = "Text.txt";

        // Initialize the S3 client
        S3Client s3Client = S3Client.builder()
                .region(Region.EU_NORTH_1) // Replace with your desired region
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();

        // Get the S3 object
        S3AsyncClient s3AsyncClient = S3AsyncClient.builder().build();

        s3AsyncClient.getObject(
                GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build(),
                new CompletionHandler<GetObjectResponse>() {

                    public void complete(GetObjectResponse getObjectResponse, Throwable throwable) {
                        if (throwable != null) {
                            // Handle error
                            throwable.printStackTrace();
                            return;
                        }
                        try (InputStream inputStream = getObjectResponse.body()) {
                            // Use the InputStream here
                            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                                String line;
                                while ((line = reader.readLine()) != null) {
                                    System.out.println(line);
                                }
                            }
                        } catch (IOException e) {
                            // Handle IO exceptions
                            e.printStackTrace();
                        }
                    }
                });
    }
}**/

