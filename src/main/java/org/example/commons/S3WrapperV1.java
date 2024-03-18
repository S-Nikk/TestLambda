package org.example.commons;

/**
//version 1 imports
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3BucketConnectionV1 {
    public static class S3FileReaderV1 {
        public static void main(String[] args) throws IOException {
            String bucketName = "lambdaartifact";
            String key = "Text.txt";


            // Initialize the S3 client
            AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

            // Get the S3 object
            S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));

            try (InputStream objectData = object.getObjectContent()) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(objectData))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                }
            }
        }
    }
}
**/
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class S3WrapperV1 {

    private AmazonS3 s3Client;

    private void createS3Client() {
        s3Client = AmazonS3ClientBuilder.standard()
                .withRegion("eu-north-1") // the defaultClient() is not working for some reason, so I made a few changes
                .build();;
    }

    public List<S3ObjectSummary> listObjects(String bucketName) {
        createS3Client();
        ObjectListing objectListing = s3Client.listObjects(bucketName);
        return objectListing.getObjectSummaries();
    }

    public S3Object getObject(String bucketName, String key) {
        createS3Client();
        S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));

        try (InputStream objectData = object.getObjectContent()) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(objectData))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return object;
    }
    public static void copyObject(AmazonS3 s3Client, String srcBucket, String srcKey, String destBucket, String destKey) {
        CopyObjectRequest copyObjRequest = new CopyObjectRequest(srcBucket, srcKey, destBucket, destKey);
        s3Client.copyObject(copyObjRequest);
    }
    public static void deleteObject(AmazonS3 s3Client, String bucketName, String objectKey) {
        DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(bucketName, objectKey);
        s3Client.deleteObject(deleteObjectRequest);
    }
}