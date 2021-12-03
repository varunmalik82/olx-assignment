package com.olx.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ApiUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static Response executeGetApi(String endPoint, Object paramsMapObject, HashMap<String, String> headerMap) {
        if (endPoint == null)
            return null;

        RequestSpecification requestSpecification = RestAssured.given().when();

        if (Objects.nonNull(paramsMapObject)) {
            Map<String, String> paramsMap = mapper.convertValue(paramsMapObject, Map.class);
            requestSpecification.queryParams(paramsMap);
        }

        if (headerMap != null)
            requestSpecification.headers(headerMap);

        return requestSpecification.get(endPoint).then().extract().response();
    }

    public static Response executePostApi(String endPoint, Object requestBody, HashMap<String, String> headerMap) {
        if (Objects.isNull(endPoint) || Objects.isNull(requestBody))
            return null;

        RequestSpecification requestSpecification = RestAssured.given().when();
        requestSpecification.accept(ContentType.JSON);

        if (headerMap != null)
            requestSpecification.headers(headerMap);

        if (requestBody != null)
            requestSpecification.body(requestBody);

        return requestSpecification.post(endPoint).then().extract().response();
    }
}