package org.example.commons;

import java.util.*;
import java.util.List;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;
import software.amazon.awssdk.services.ssm.model.Parameter;

public class CustomSSMClient {
    public static String GetSingleParam(String ParamName) {
        // Set the AWS region where your SSM parameter is stored
        String parameterValue = null;
        Region region = Region.EU_NORTH_1;

        // Create an SSM client
        SsmClient ssmClient = SsmClient.builder()
                .region(region)
                .build();

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
        return parameterValue;}
    public static List<String> GetMultiParam(List<String> ParamNames) {

        String parameterValue = null;
        Region region = Region.EU_NORTH_1;

        // Create an SSM client
        SsmClient ssmClient = SsmClient.builder()
                .region(region)
                .build();

        try {
            GetParametersRequest parametersRequest = GetParametersRequest.builder()
                    .names(ParamNames)
                    .withDecryption(true)
                    .build();

            // Retrieve the parameter values
            GetParametersResponse parametersResponse = ssmClient.getParameters(parametersRequest);
            List<Parameter> parameterList = parametersResponse.parameters();

            // Process the parameter values as needed
            List<String> parameterValues = new ArrayList<>();
            for (Parameter parameter : parameterList) {
                String ParamType = String.valueOf(parameter.type());
                if ("String".equals(ParamType) || "StringList".equals(ParamType)) {
                    parameterValues.add(parameter.value());
                } else if ("SecureString".equals(ParamType)) {
                    // Retrieve the secure parameter value
                    parameterValues.add(parameter.value());
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
        return ParamNames;
    }
}

