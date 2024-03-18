package org.example;
/**
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.commons.S3WrapperV1;
import org.example.commons.S3WrapperV2;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.IOException;

public class HandlerLambda implements RequestHandler<Object, String> {

        public S3BucketHandler s3BucketHandler = null;
        public HandlerLambda() {
                //S3WrapperV1 s3V1Wrapper = new S3WrapperV1();
                S3WrapperV2 s3V2Wrapper = new S3WrapperV2();
                s3BucketHandler = new S3BucketHandler( /**s3V1Wrappers3V2Wrapper);
            }

        @Override
        public String handleRequest(Object input, Context context) {
            new HandlerLambda();
            try {
                S3BucketHandler.test();
                return "Successfully executed S3BucketHandler.main()";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
**/
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.example.commons.S3WrapperV2;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Response;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

public class HandlerLambda implements RequestHandler<Object, String> {

    private final S3WrapperV2 s3V2Wrapper;

    public HandlerLambda() {
        s3V2Wrapper = new S3WrapperV2();
    }

    @Override
    public String handleRequest(Object input, Context context) {
        String bucketName = "lambdaartifact";
        String key = "Text.txt";

        // List objects in the bucket using AWS SDK v2
        System.out.println("Listing objects in the bucket using AWS SDK v2:");
        ListObjectsV2Request listObjectsRequest = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();
        ListObjectsV2Response listObjectsResponse = s3V2Wrapper.listObjects(bucketName);
        listObjectsResponse.contents().forEach(s3Object -> System.out.println(s3Object.key()));

        // Get the details of an object in the bucket using AWS SDK v2
        System.out.println("\nGetting object details using AWS SDK v2:");
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        GetObjectResponse objectV2 = s3V2Wrapper.getObject(bucketName, key);
        return "Passed";
    }}