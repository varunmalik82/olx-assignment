package com.olx.core.pojos;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ApiConfigs {
    private String name;
    private String endPoint;
    private Map<String, String> headers;
}
