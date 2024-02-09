import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import pages.LoginPage;
import pages.HomePage;

public class HomePageTest {

    public WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "\"C:\\Users\\MYPC\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe\"");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.loginToApplication("142420", "231225");
        String expectedUrl = "https://qaebank.ccbp.tech/";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    @AfterMethod
    public void tearDown(){driver.quit();}

    @Test(priority = 1)
    public void testHeadingText(){
        Assert.assertTrue(homePage.findDigitalCard().isDisplayed(), "Digital card image is not displayed");
        Assert.assertTrue(homePage.findLogoImg().isDisplayed(), "App logo is not displayed" );
        String expectedText = "Your Flexibility, Our Excellence";
        String actualHeading = homePage.findHeadingtext();
        Assert.assertEquals(actualHeading,expectedText,  "Heading text does not match");
    }

    @Test(priority = 2)
    public void testLogoutFunction(){
        homePage.findLogoutButton();
        String expectedUrl = "https://qaebank.ccbp.tech/ebank/login";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URLs do not match");
    }
}
