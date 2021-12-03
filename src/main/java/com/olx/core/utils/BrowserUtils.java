package com.olx.core.utils;

import com.olx.core.enums.BrowserTypeEnum;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Objects;

public class BrowserUtils {
    private static WebDriver driver = null;
    private static HashMap<BrowserTypeEnum, WebDriver> driverMap = new HashMap<>();
    private static BrowserTypeEnum browser;

    public static synchronized WebDriver getDriver() {

        if (Objects.nonNull(System.getProperty("browser")))
            browser = BrowserTypeEnum.valueOf(System.getProperty("browser").toUpperCase());
        else
            browser = BrowserTypeEnum.valueOf(ConfigUtils.getProperty("browser").toUpperCase());

        if (driverMap.containsKey(browser))
            return driverMap.get(browser);

        switch (browser) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            }
            default: {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
        }

        driver.manage().window().maximize();
        driverMap.put(browser, driver);

        return driverMap.get(browser);
    }

    public static synchronized void closeBrowser() {
        if (driver != null)
            driver.quit();
    }
}
