package com.olx.api.imdbsearchapi.pojos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPojoSearchApi {
    private String apiKey;
    private String type;
    private String s;
}