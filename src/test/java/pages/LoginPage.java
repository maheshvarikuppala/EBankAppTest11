package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {

    By loginImageLocator = By.className("login-ebank-image");
    By labelsLocator = By.className("input-label");
    By loginHeadingLocator = By.className("login-heading");
    By userIdLocator = By.id("userIdInput");
    By pinLocator = By.id("pinInput");
    By loginButtonLocator = By.className("login-button");
    By errorMessageLocator = By.className("error-message-text");

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement findLoginImageElement() {
        return driver.findElement(loginImageLocator);
    }

    public String getLabelTextAt(int index) {
        return driver.findElements(labelsLocator).get(index).getText();
    }

    public String getLoginHeadingText() {
        return driver.findElement(loginHeadingLocator).getText();
    }

    public void enterUserId(String userId){
        driver.findElement(userIdLocator).sendKeys(userId);
    }

    public void enterPin(String pin){
        driver.findElement(pinLocator).sendKeys(pin);
    }

    public void clickOnLoginButton(){
        driver.findElement(loginButtonLocator).click();
    }

    public void loginToApplication(String userId, String pin){
        enterUserId(userId);
        enterPin(pin);
        clickOnLoginButton();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator)).getText();
    }

}
