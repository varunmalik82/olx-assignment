package com.olx.desktop.pages.imdb;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class SearchPage {
    @FindBy(how = How.XPATH, using = "//h3[.='Titles']/following-sibling::table//td[@class='result_text']/a")
    private List<WebElement> searchResults;
    final static Logger logger = Logger.getLogger(SearchPage.class);


    public void searchInMovieResults(String text) {
        try{
            for (WebElement e : searchResults) {
                if (e.getText().equalsIgnoreCase(text)) {
    //                System.out.println("\n Movie [" + text + "] available in movie results \n");
                    logger.info("\n Movie [" + text + "] available in movie results \n");
                    break;
                }
            }
        }catch (Exception e){
            logger.error("Exception occurred while searching for ["+text+"]", e);
        }
    }
}
