package com.olx.api.imdbsearchapi;

import com.olx.api.imdbsearchapi.pojos.response.ResponsePojoSearchApi;
import com.olx.core.ApiTask;
import com.olx.core.utils.ApiUtils;
import com.olx.core.utils.JsonUtils;
import io.restassured.response.Response;
import org.testng.Assert;

public class GetResponseTaskSearchApi implements ApiTask<TaskManagerSearchApi> {

    @Override
    public TaskManagerSearchApi perform(TaskManagerSearchApi objTaskManagerSearchApi) {

        String endpoint = objTaskManagerSearchApi.getApiConfigs().getEndPoint();

        // Hit API and get response
        Response response = ApiUtils.executeGetApi(endpoint, objTaskManagerSearchApi.getRequest(), null);

        // Fail test case if API response code is not 200
        Assert.assertEquals(response.getStatusCode(), 200, "Invalid response code received");

        // Set API response to TaskManager obj
        ResponsePojoSearchApi responseSearchApi = JsonUtils.convertJsonToObject(response.asString(),
                ResponsePojoSearchApi.class);
        objTaskManagerSearchApi.setResponse(responseSearchApi);

        return objTaskManagerSearchApi;
    }
}
