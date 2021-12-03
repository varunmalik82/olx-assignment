package com.olx.desktop.pages.imdb;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    @FindBy(how = How.ID, using = "suggestion-search")
    private WebElement suggestionSearchBar;

    @FindBy(how = How.ID, using = "suggestion-search-button")
    private WebElement suggestionSearchBtn;

    public void searchFor(String text) {
        suggestionSearchBar.sendKeys(text);
        suggestionSearchBtn.submit();
    }
}
