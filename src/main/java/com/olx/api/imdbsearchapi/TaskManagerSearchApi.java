package com.olx.api.imdbsearchapi;

import com.olx.api.imdbsearchapi.pojos.request.RequestPojoSearchApi;
import com.olx.api.imdbsearchapi.pojos.response.ResponsePojoSearchApi;
import com.olx.core.pojos.ApiConfigs;
import com.olx.core.ApiTaskManager;
import com.olx.core.pojos.TestDataRow;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskManagerSearchApi extends ApiTaskManager<TaskManagerSearchApi> {

    private TestDataRow testData;
    private RequestPojoSearchApi request;
    private ResponsePojoSearchApi response;
    private ApiConfigs apiConfigs;

    // Call create request, get response and validate response tasks
    public void validateImdbSearchApi(){
        super.addTask(new CreateRequestTaskSearchApi());
        super.addTask(new GetResponseTaskSearchApi());
        super.addTask(new ValidateResponseTaskSearchApi());
        super.execute(this);
    }

}
