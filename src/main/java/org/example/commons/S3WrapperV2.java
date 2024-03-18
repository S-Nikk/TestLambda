package org.example.commons;

/**import software.amazon.awssdk.core.ResponseBytes;
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
}**/
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import java.util.List;

public class S3WrapperV2 {

    private S3Client s3Client;

    private void createS3Client() {
        s3Client = S3Client.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }

    public ListObjectsV2Response listObjects(String bucketName) {
        createS3Client();
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();
        return s3Client.listObjectsV2(listObjectsV2Request);
    }

    public GetObjectResponse getObject(String bucketName, String key) {
        createS3Client();
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();
        ResponseBytes<GetObjectResponse> responseBytes = s3Client.getObjectAsBytes(getObjectRequest);

        // Convert the byte array to a string
        String objectContent = responseBytes.asUtf8String();
        System.out.println(objectContent);
        return responseBytes.response();

    }
    public void copyObject(String accessKey, String secretKey, String region, String srcBucket, String srcKey, String destBucket, String destKey) {
        createS3Client();
        CopyObjectRequest copyObjRequest = CopyObjectRequest.builder()
                .sourceBucket(srcBucket)
                .sourceKey(srcKey)
                .destinationBucket(destBucket)
                .destinationKey(destKey)
                .build();
        s3Client.copyObject(copyObjRequest);
    }
    public void deleteObject(String accessKey, String secretKey, String region, String bucketName, String objectKey) {
        createS3Client();
        DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key(objectKey)
                .build();
        s3Client.deleteObject(deleteObjectRequest);
    }


}