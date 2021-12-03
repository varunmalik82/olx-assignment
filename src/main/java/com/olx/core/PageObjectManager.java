package com.olx.core;

import com.olx.core.utils.BrowserUtils;
import com.olx.desktop.pages.imdb.HomePage;
import com.olx.desktop.pages.imdb.SearchPage;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

public class PageObjectManager {
    private static HomePage homePage;
    private static SearchPage searchPage;

    public static synchronized HomePage getHomePageInstance() {
        return Objects.nonNull(homePage) ? homePage : PageFactory.initElements(BrowserUtils.getDriver(),
                HomePage.class);
    }

    public static synchronized SearchPage getSearchPageInstance() {
        return Objects.nonNull(searchPage) ? searchPage : PageFactory.initElements(BrowserUtils.getDriver(),
                SearchPage.class);
    }
}
