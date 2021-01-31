package com.podium.web.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.asserts.SoftAssert;

import com.podium.web.pages.PodiumLandingPage;

public class TestUtils {
    /**
     * Validates product menu
     * @param driver : WebDriver instance
     * @param landingPage : LandingPage
     * @param report : to assert
     */
    public static void validateProductsMenu(WebDriver driver, PodiumLandingPage landingPage, SoftAssert report) {
        Actions builder = new Actions(driver);
        builder.moveToElement(landingPage.getProductsMenu()).build().perform();
        landingPage.getReviewsMenu().click();
        report.assertTrue(driver.getTitle().contains("Customer Feedback") == true,
                "Redirection to Reviews page is incorrect");
        driver.navigate().back();

        builder.moveToElement(landingPage.getProductsMenu()).build().perform();
        landingPage.getPaymentsMenu().click();
        report.assertTrue(driver.getTitle().contains("Mobile Payment Processing") == true,
                "Redirection to Payments page is incorrect");
        driver.navigate().back();
    }

    /**
     * Validates images on landing page
     * @param driver : WebDriver instance
     * @param landingPage : LandingPage
     * @param report : to assert
     */
    public static void validateImages(WebDriver driver, PodiumLandingPage landingPage, SoftAssert report) {
        List<WebElement> allImages = landingPage.getLandingPageImages();
        for (WebElement img : allImages) {
            // check image natural width
            if (img.getAttribute("src") != null && img.getAttribute("style").equalsIgnoreCase("display:none")) {
                boolean isImageDisplayed = (Boolean) ((JavascriptExecutor) driver).executeScript(
                        "return (typeof arguments[0].naturalWidth !=\"undefined\" && arguments[0].naturalWidth > 0);",
                        img) == true;
                report.assertTrue(isImageDisplayed == true, "This image is broken : " + img.getAttribute("src"));
            }
        }
    }

    /**
     * Reads env from property file
     * @return : env from property file
     */
    public static String getEnv() {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("env.properties")) {
            // load a properties file
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop.getProperty("runtestenv");
    }

    /**
     * Gets Chrome driver
     * @param driver : WebDriver instance
     * @return : initiated driver
     */
    public static WebDriver getLocalWebDriver(WebDriver driver) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Chromedriver.exe");
        driver = new ChromeDriver();
        // Maximize browser window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.get("https://www.podium.com");
        return driver;
    }

    /**
     * Gets Remote Chrome driver
     * @param driver : WebDriver instance
     * @return : initiated Remote driver
     */
    public static WebDriver getRemoteWebDriver(WebDriver driver) throws MalformedURLException {
        DesiredCapabilities dc = DesiredCapabilities.chrome();
        URL url = new URL("http://localhost:4444/wd/hub");
        driver = new RemoteWebDriver(url, dc);
        driver.manage().window().maximize();
        driver.get("https://www.podium.com/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        return driver;
    }
}
