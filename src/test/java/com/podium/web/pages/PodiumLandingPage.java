package com.podium.web.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PodiumLandingPage {
    // Webdriver instance
    WebDriver driver;

    public PodiumLandingPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Gets Podium logo element from webpage
     *
     * @return : webelement
     */
    public WebElement getLogo() {
        return driver.findElement(Using.PODIUM_LOGO.selector);
    }

    /**
     * Gets the title of current webpage
     *
     * @return : title of page
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * Gets Products mega menu web element
     *
     * @return : Webelement
     */
    public WebElement getProductsMenu() {
        return driver.findElement(Using.PRODUCTS_MEGA_MENU.selector);
    }

    /**
     * Gets Reviews menu item web element
     *
     * @return :  Webelement
     */
    public WebElement getReviewsMenu() {
        return driver.findElement(Using.REVIEWS_MENU_ITEM.selector);
    }

    /**
     * Gets Payments menu item web element
     *
     * @return : Webelement
     */
    public WebElement getPaymentsMenu() {
        return driver.findElement(Using.PAYMENTS_MENU_ITEM.selector);
    }

    /**
     * Gets Watch Demo button web element
     *
     * @return : Webelement
     */
    public WebElement getWatchDemoButton() {
        return driver.findElement(Using.WATCH_DEMO_BUTTON.selector);
    }

    /**
     * Gets list of web elements for all the images on the page
     *
     * @return :  List of Webelements
     */
    public List<WebElement> getLandingPageImages() {
        return driver.findElements(Using.LANDING_PAGE_IMAGES.selector);
    }

    /**
     * Waits until driver is able to switch to chat bubble frame
     *
     * @param wait : wait with time defined
     */
    public void waitForChatBubble(WebDriverWait wait) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(Using.CHAT_BUBBLE_FRAME.selector));
    }

    /**
     * Gets web element for chat bubble
     *
     * @return : Webelement
     */
    public WebElement getChatBubble() {
        return driver.findElement(Using.CHAT_BUBBLE.selector);
    }

    /**
     * Plays video for Trane testimonial
     *
     */
    public void playTraneVideo() {
        driver.findElement(Using.TRANE_VEDEO_PLAY_BUTTON.selector).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(Using.TRANE_VEDEO_CLOSE_BUTTON.selector).click();
    }

    /**
     * Plays video for Austin Couch Potato testimonial
     *
     */
    public void playAustinCouchPotatoVideo() {
        driver.findElement(Using.AUSTIN_COUCH_POTATO_PLAY_BUTTON.selector).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(Using.AUSTIN_COUCH_POTATO_CLOSE_BUTTON.selector).click();
    }

    /**
     * Clicks on right arrow in video slider
     */
    public void clickRightVideoArrow() {
        WebElement rightArrow = driver.findElement(Using.VIDEO_CONTAINER.selector).findElement(Using.VIDEO_RIGHT_ARROW.selector);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(rightArrow));
        rightArrow.click();
    }

    //  Enums definitions for Web elements on web page
    private enum Using {
        PODIUM_LOGO(By.xpath("//*[@id=\"theme-white\"]/nav/div/a/img")),
        // Products Menu related web elements
        PRODUCTS_MEGA_MENU(By.xpath("//*[@id=\"theme-white\"]/nav/div/div[1]/ul/li[1]")),
        REVIEWS_MENU_ITEM(By.xpath("//a[@href='/feedback']//span[@class='submenu-thumbnail']")),
        PAYMENTS_MENU_ITEM(By.xpath("//a[@href='/payments']//span[@class='submenu-thumbnail']")),

        WATCH_DEMO_BUTTON(By.xpath(
                "//div[@id='s-text-rtp']//div[@id='mobile-row-stack']//div[@id='primary']//a[@class='deskLink'][contains(text(),'Watch Demo')]")),

        LANDING_PAGE_IMAGES(By.tagName("img")),

        CHAT_BUBBLE_FRAME(By.xpath("//*[@id=\"podium-bubble\"]")),
        CHAT_BUBBLE(By.xpath("//div[@class='ContactBubble__IconContainer']")),

        // Testimonial video web elements
        TRANE_VEDEO_PLAY_BUTTON(By.id("video-1-img")),
        TRANE_VEDEO_CLOSE_BUTTON(By.xpath("//*[@id=\"wistia-vvd1bukwgt-1_popover_popover_close_button\"]/img")),
        AUSTIN_COUCH_POTATO_PLAY_BUTTON(By.id("video-2-img")),
        AUSTIN_COUCH_POTATO_CLOSE_BUTTON(
                By.xpath("//button[@id='wistia-sw4vtcuydu-1_popover_popover_close_button']/img")),
        VIDEO_CONTAINER(By.id("video-slider")),
        VIDEO_RIGHT_ARROW(By.id("right-arrow-video"));

        By selector;

        Using(By selector) {
            this.selector = selector;
        }

        By selector() {
            return this.selector;
        }
    }
}
