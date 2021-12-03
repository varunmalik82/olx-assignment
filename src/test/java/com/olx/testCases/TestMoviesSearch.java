package com.olx.testCases;

import com.olx.api.imdbsearchapi.TaskManagerSearchApi;
import com.olx.api.imdbsearchapi.pojos.response.Search;
import com.olx.core.PageObjectManager;
import com.olx.core.pojos.ApiConfigs;
import com.olx.core.pojos.TestDataRow;
import com.olx.core.utils.BrowserUtils;
import com.olx.core.utils.ConfigUtils;
import com.olx.core.utils.TestDataUtils;
import com.olx.desktop.pages.imdb.HomePage;
import com.olx.desktop.pages.imdb.SearchPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class TestMoviesSearch {

    private final String apiName = "imdbSearchApi";
    private ApiConfigs apiConfigs;
    private TaskManagerSearchApi objTaskManagerSearchApi;
    private WebDriver driver;
    final static Logger logger = Logger.getLogger(TestMoviesSearch.class);

    @BeforeClass
    public void setUp() {
        apiConfigs = ConfigUtils.getApiConfig(apiName);
        driver = BrowserUtils.getDriver();
    }

    @DataProvider
    public Object[][] testDataProvider() {
        // Read test data from TestData.json
        return TestDataUtils.getTestData();
    }

    @Test(dataProvider = "testDataProvider")
    public void testImdbSearch(TestDataRow testDataRow) {
        // Hit IMDB search api and filter response to have only expected movie titles
        logger.debug("\n Going to hit IMDB search api and filter response to have only expected movie titles \n");
        objTaskManagerSearchApi = new TaskManagerSearchApi();
        objTaskManagerSearchApi.setApiConfigs(this.apiConfigs);
        objTaskManagerSearchApi.setTestData(testDataRow);
        objTaskManagerSearchApi.validateImdbSearchApi();

        // Search for 'lord of the rings' on IMDB home page and look for expected movies (filtered in api response) on search page
        logger.debug("\n Going to search for 'lord of the rings' on IMDB home page and look for expected movies (filtered in api response) on search page \n");
        driver.get(ConfigUtils.getProperty("imdbHomePageUrl"));
        HomePage imdbHomePage = PageObjectManager.getHomePageInstance();
        imdbHomePage.searchFor(objTaskManagerSearchApi.getTestData().getValue("s"));
        SearchPage imdbSearchPage = PageObjectManager.getSearchPageInstance();
        List<Search> filteredSearchResults = objTaskManagerSearchApi.getResponse().getSearch();
        for (Search s : filteredSearchResults) {
            imdbSearchPage.searchInMovieResults(s.getTitle());
        }
    }

    @AfterClass
    public void tearDown() {
        BrowserUtils.closeBrowser();
    }
}
