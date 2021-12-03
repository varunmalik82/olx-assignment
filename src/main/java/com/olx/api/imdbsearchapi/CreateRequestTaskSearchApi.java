package com.olx.api.imdbsearchapi;

import com.olx.api.imdbsearchapi.pojos.request.RequestPojoSearchApi;
import com.olx.core.ApiTask;
import com.olx.core.pojos.TestDataRow;


public class CreateRequestTaskSearchApi implements ApiTask<TaskManagerSearchApi> {
    TaskManagerSearchApi objTaskManagerSearchApi;
    TestDataRow testData;

    @Override
    public TaskManagerSearchApi perform(TaskManagerSearchApi objTaskManagerSearchApi) {
        this.objTaskManagerSearchApi = objTaskManagerSearchApi;
        this.testData = objTaskManagerSearchApi.getTestData();

        //set headers
        // In this API, no headers required, so no need to do anything here

        //set request body
        this.setRequestBody();

        return objTaskManagerSearchApi;
    }

    private void setRequestBody(){
        RequestPojoSearchApi request = new RequestPojoSearchApi();

        request.setApiKey(objTaskManagerSearchApi.getTestData().getValue("apikey"));
        request.setType(objTaskManagerSearchApi.getTestData().getValue("type"));
        request.setS(objTaskManagerSearchApi.getTestData().getValue("s"));

        objTaskManagerSearchApi.setRequest(request);
    }
}
