package org.example.commons;

import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import java.nio.charset.StandardCharsets;

public class S3BucketConnectionV2 {
    public static void main(String[] args) {
        // Create an S3Client object
        S3Client s3Client = S3Client.builder()
                .region(Region.EU_NORTH_1)
                .build();

        // Set up the GetObjectRequest object
        String bucketName = "lambdaartifact";
        String objectKey = "Text.txt";
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();

        // Get the object as a byte array
        ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(getObjectRequest);

        // Convert the byte array to a string
        String objectContent = responseBytes.asUtf8String();

        // Print the object content
        System.out.println(objectContent);
    }
}