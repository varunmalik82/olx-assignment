package com.olx.api.imdbsearchapi.pojos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search {

    @JsonProperty("Title")
    private String title;

    @JsonProperty("Year")
    private String year;

    @JsonProperty("imdbID")
    private String imdbID;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Poster")
    private String poster;
}