package com.olx.core.utils;

import com.olx.core.pojos.ApiConfigs;
import com.olx.core.pojos.ApiConfigsList;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class ConfigUtils {

    private final static String YAML_FILE_LOCATION = System.getProperty("user.dir") + "/src/test/resources" +
            "/ApiConfigData.yaml";
    private final static String PROPERTY_FILE_LOCATION = System.getProperty("user.dir") + "/src/test/resources/Config" +
            ".properties";
    private static Yaml yaml;
    private static Properties properties;

    public static synchronized ApiConfigs getApiConfig(String apiName) {
        Map<String, ApiConfigs> apiConfigsMap = populateYaml();
        return apiConfigsMap != null ? apiConfigsMap.get(apiName) : null;
    }

    public static synchronized String getProperty(String propertyKey) {
        try {
            if (properties == null)
                properties = new Properties();
            FileReader reader = new FileReader(PROPERTY_FILE_LOCATION);
            properties.load(reader);
            return properties.getProperty(propertyKey);
        } catch (Exception e) {
            System.err.println("Exception occurred while reading property from Config.properties" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private static synchronized Map<String, ApiConfigs> populateYaml() {
        Map<String, ApiConfigs> apiConfigsMap = new HashMap<>();
        try {
            if (yaml == null)
                yaml = new Yaml();
            InputStream stream = new FileInputStream(YAML_FILE_LOCATION);
            ApiConfigsList apiConfigsList = yaml.loadAs(stream, ApiConfigsList.class);
            List<ApiConfigs> apiList = apiConfigsList.getApiList();
            for (ApiConfigs apiConfigs : apiList) {
                apiConfigsMap.put(apiConfigs.getName(), apiConfigs);
            }
            return apiConfigsMap;
        } catch (Exception e) {
            System.err.println("Exception occurred while parsing data from ApiConfigData.yaml" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
