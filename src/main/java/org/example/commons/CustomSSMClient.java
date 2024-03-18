package org.example.commons;

import java.util.*;
import java.util.List;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;
import software.amazon.awssdk.services.ssm.model.Parameter;
import software.amazon.awssdk.services.ssm.model.

public class CustomSSMClient {

    private static SsmClient ssmClient=null;
    private static void getClient(){

        Region region = Region.EU_NORTH_1;

        // Create an SSM client
        SsmClient ssmClient = SsmClient.builder()
                .region(region)
                .build();
    }
    public static String getSingleParam(String ParamName) {
        // Set the AWS region where your SSM parameter is stored
        getClient();
        String parameterValue = null;
        try {
            // Specify the parameter name
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(ParamName)
                    .build();

            // Retrieve the parameter value
            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
            parameterValue = parameterResponse.parameter().value();

        } catch (Exception e) {
            // Handle any exceptions (e.g., parameter not found, permission issues)
            System.out.println("Error retrieving the parameter: " + e.getMessage());

        } finally {

            // Close the SSM client
            ssmClient.close();
        }
        return parameterValue;
    }
    public static Map<String, String> getMultiParam(List<String> ParamNames) {

        String parameterValue = null;
        getClient();

        try {
            GetParametersRequest parametersRequest = GetParametersRequest.builder()
                    .names(ParamNames)
                    .withDecryption(true)
                    .build();

            // Retrieve the parameter values
            GetParametersResponse parametersResponse = ssmClient.getParameters(parametersRequest);
            List<Parameter> parameterList = parametersResponse.parameters();

            // Process the parameter values as needed

            Map<String, String> parameterValues = new HashMap<>();
            for (Parameter parameter : parameterList) {
                String paramType = String.valueOf(parameter.type());
                if ("String".equals(paramType) || "StringList".equals(paramType)) {
                    parameterValues.put(parameter.name(), parameter.value());
                } else if ("SecureString".equals(paramType)) {
                    // Retrieve the secure parameter value

                    parameterValues.put(parameter.name(), parameter.value());
                }
            return parameterValues;
            }
        } catch (Exception e) {
            // Handle any exceptions (e.g., parameter not found, permission issues)
            System.out.println("Error retrieving the parameter: " + e.getMessage());

        } finally {

            // Close the SSM client
            ssmClient.close();

        }
        return null;
    }
}

