package com.olx.api.imdbsearchapi;

import com.olx.api.imdbsearchapi.pojos.response.ResponsePojoSearchApi;
import com.olx.api.imdbsearchapi.pojos.response.Search;
import com.olx.core.ApiTask;
import com.olx.core.utils.JsonUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidateResponseTaskSearchApi implements ApiTask<TaskManagerSearchApi> {
    final static Logger logger = Logger.getLogger(ValidateResponseTaskSearchApi.class);

    @Override
    public TaskManagerSearchApi perform(TaskManagerSearchApi objTaskManagerSearchApi) {

        String[] expectedMovies = objTaskManagerSearchApi.getTestData().getValue("expectedMovieList").split(",");
        ArrayList<String> expectedMovieList = new ArrayList<>();
        Collections.addAll(expectedMovieList, expectedMovies);

        ResponsePojoSearchApi response = objTaskManagerSearchApi.getResponse();
//        System.out.println("\n API response before filtering: \n" + JsonUtils.convertObjectToJson(response));
        logger.info("\n API response before filtering: \n" + JsonUtils.convertObjectToJson(response));

        // Filter api response to have only expectedMovieList
        List<Search> searchList = response.getSearch();
        searchList.removeIf(search -> !expectedMovieList.contains(search.getTitle()));
        response.setSearch(searchList);

        // Set filtered response to task manager obj
        objTaskManagerSearchApi.setResponse(response);

//        System.out.println("\n API response after filtering: \n " + JsonUtils.convertObjectToJson(response));
        logger.info("\n API response after filtering: \n " + JsonUtils.convertObjectToJson(response));

        return objTaskManagerSearchApi;
    }
}
