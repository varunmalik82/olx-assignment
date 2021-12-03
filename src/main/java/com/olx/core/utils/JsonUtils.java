package com.olx.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T convertJsonToObject(String jsonString, Class<T> typeClass) {
        if (jsonString == null || jsonString.trim().equals(""))
            return null;
        try {
            return MAPPER.readValue(jsonString, typeClass);
        } catch (Exception e) {
            System.err.println("Exception occurred while converting json String to Object"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static String convertObjectToJson(Object obj) {
        if (Objects.isNull(obj))
            return null;
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            System.err.println("Exception occurred while converting object to json String"+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
