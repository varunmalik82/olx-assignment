package com.olx.api.imdbsearchapi.pojos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponsePojoSearchApi {

    @JsonProperty("Search")
    private List<Search> search;

    private String totalResults;

    @JsonProperty("Response")
    private String response;

}

