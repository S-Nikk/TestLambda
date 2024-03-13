package org.example;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.example.commons.S3WrapperV1;
import org.example.commons.S3WrapperV2;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.util.List;

public class S3BucketHandler {

    private final S3WrapperV1 s3V1Wrapper;
    private final S3WrapperV2 s3V2Wrapper;

    public S3BucketHandler(S3WrapperV1 s3V1Wrapper, S3WrapperV2 s3V2Wrapper) {
        this.s3V1Wrapper = s3V1Wrapper;
        this.s3V2Wrapper = s3V2Wrapper;
    }
    public static void main(String[] args) throws IOException {
        String bucketName = "lambdaartifact";
        String key = "Text.txt";

        S3WrapperV1 s3V1Wrapper = new S3WrapperV1();
        S3WrapperV2 s3V2Wrapper = new S3WrapperV2();

        S3BucketHandler s3BucketHandler = new S3BucketHandler(s3V1Wrapper, s3V2Wrapper);

        // List objects in the bucket using AWS SDK v1
        System.out.println("Listing objects in the bucket using AWS SDK v1:");
        List<S3ObjectSummary> objectsV1 = s3BucketHandler.s3V1Wrapper.listObjects(bucketName);
        for (S3ObjectSummary objectSummary : objectsV1) {
            System.out.println(objectSummary.getKey());
        }

        // List objects in the bucket using AWS SDK v2
        System.out.println("\nListing objects in the bucket using AWS SDK v2:");
        List<S3Object> objectsV2 = s3BucketHandler.s3V2Wrapper.listObjects(bucketName);
        for (S3Object object : objectsV2) {
            System.out.println(object.key());
        }

        // Get the details of an object in the bucket using AWS SDK v1
        System.out.println("\nGetting object details using AWS SDK v1:");
        com.amazonaws.services.s3.model.S3Object objectV1 = s3BucketHandler.s3V1Wrapper.getObject(bucketName, key);
        System.out.println("Content type: " + objectV1.getObjectMetadata().getContentType());

        // Get the details of an object in the bucket using AWS SDK v2
        System.out.println("\nGetting object details using AWS SDK v2:");
        GetObjectResponse objectV2 = s3BucketHandler.s3V2Wrapper.getObject(bucketName, key);
        System.out.println("Content type: " + objectV2.contentType());
    }
}