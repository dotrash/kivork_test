package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private static final int DEFAULT_TIMEOUT = 5;
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @FindBy(xpath = "//div[@id='loginButton']")
    private WebElement loginButton;

    //The AjaxElementLocatorFactory approach (preferable -> implicit wait for each element)
    protected HomePage(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, DEFAULT_TIMEOUT), this);
    }

    //The refresh approach (dummy)
    public void clickLoginButtonWithRefresh() {
        By loginButtonLocator = By.id("loginButton");
        try {
            driver.findElement(loginButtonLocator).click();
        } catch (StaleElementReferenceException e) {
            driver.navigate().refresh();
            driver.findElement(loginButtonLocator).click();
        }
    }

    //The wait approach (more intelligent)
    public void clickLoginButtonWithWait() {
        By loginButtonLocator = By.id("loginButton");
        webDriverWait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        driver.findElement(loginButtonLocator).click();
    }

    //wait for page to unload
    public void waitForPageToUnload() {
        By loginButtonLocator = By.id("loginButton"); // marker for the starting page
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(loginButtonLocator));
    }
}
