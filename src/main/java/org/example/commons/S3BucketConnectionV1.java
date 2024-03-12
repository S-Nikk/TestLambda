package org.example.commons;


//version 1 imports
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

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
            AmazonS3 s3Client = new AmazonS3Client(new ProfileCredentialsProvider());

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
