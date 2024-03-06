package org.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import static org.example.commons.CustomSSMClient.getSingleParam;

public class TestLambda implements RequestHandler<Object, String> {


    // In case we want to assign a default value later on
    public String ParamName;

    @Override
    public String handleRequest(Object input, Context context) {
        ParamName = getSingleParam("ENV");
        System.out.println(ParamName);
        return ParamName;
    }
}