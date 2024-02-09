package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;


public class HomePage {

    WebDriver driver;

    By getImgLogo = By.className("ebank-logo");
    By getHeadingText = By.className("heading");
    By getDigitalCard = By.className("digital-card-image");
    By getLogoutButton = By.className("logout-button");

    public HomePage(WebDriver driver){this.driver = driver;}
    public WebElement findLogoImg(){return driver.findElement(getImgLogo);}
    public WebElement findDigitalCard(){return driver.findElement(getDigitalCard);}
    public String findHeadingtext(){return  driver.findElement(getHeadingText).getText();}
    public void findLogoutButton(){driver.findElement(getLogoutButton).getText();}

}
