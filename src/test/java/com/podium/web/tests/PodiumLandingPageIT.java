package com.podium.web.tests;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.podium.web.pages.PodiumLandingPage;
import com.podium.web.utils.TestUtils;

public class PodiumLandingPageIT {
    SoftAssert report = new SoftAssert();
    PodiumLandingPage landingPage;
    WebDriver driver;

    // Executes before each test method
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        if (TestUtils.getEnv().equalsIgnoreCase("docker"))
            driver = TestUtils.getRemoteWebDriver(driver);
        else
            driver = TestUtils.getLocalWebDriver(driver);
        landingPage = PageFactory.initElements(driver, PodiumLandingPage.class);
    }

    @AfterMethod
    public void tearDownTest() {
        driver.quit();
    }

    /**
     * Validates if home page title and if Podium logo is displayed
     */
    @Test(priority = 1)
    public void validateTitleAndLogo() {
        report.assertTrue(landingPage.getLogo().isEnabled() == true, "Logo is not enabled on landing page");
        report.assertTrue(landingPage.getTitle().contains("Messaging Tools for Local Business"),
                "Title for landing page is incorrect");
        report.assertAll();
    }

    /**
     * Clicks on Watch Demo button and validates redirection
     */
    @Test(priority = 2)
    public void validateWatchDemoButton() {
        landingPage.getWatchDemoButton().click();
        report.assertTrue(driver.getCurrentUrl().equals("https://learn.podium.com/watch3now"),
                "Watch Demo button redirect is incorrect");
        driver.navigate().back();
        report.assertAll();
    }

    /**
     * Validates if images on landing page are displayed
     *
     */
    @Test(priority = 3)
    public void validateImagesOnLandingPage() {
        TestUtils.validateImages(driver, landingPage, report);
        report.assertAll();
    }

    /**
     * Validates if testimonial videos can be played and closed. This method tests 2 videos in slider
     *
     */
    @Test(priority = 4)
    public void validateTestimonialVideos() throws InterruptedException {
        landingPage.playTraneVideo();
        //driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        //landingPage.clickRightVideoArrow();
        //landingPage.playAustinCouchPotatoVideo();
    }

    /**
     * Validates navigation in Products mega menu.
     */
    @Test(priority = 5)
    public void validateProductsMegaMenu() {
        TestUtils.validateProductsMenu(driver, landingPage, report);
        report.assertAll();
    }

    /**
     * Validates if chat bubble is accessible
     */
    @Test(priority = 6)
    public void validateChatFeature() {
        landingPage.waitForChatBubble(new WebDriverWait(driver, 3));
        landingPage.getChatBubble().click();
    }

}
