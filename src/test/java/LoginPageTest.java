import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

import pages.LoginPage;

public class LoginPageTest {

    public WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\MYPC\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");
        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testLoginPageUI() {
        Assert.assertTrue(loginPage.findLoginImageElement().isDisplayed(), "Login image is not displayed");

        Assert.assertEquals(loginPage.getLabelTextAt(0), "User ID", "User id label text does not match");

        Assert.assertEquals(loginPage.getLabelTextAt(1), "PIN", "Pin label text does not match");

        String actualErrorMessage = loginPage.getLoginHeadingText();
        Assert.assertEquals(actualErrorMessage, "Welcome Back!", "Heading text does not match");
    }

    @Test(priority = 1)
    public void testLoginWithEmptyInputs() {
        loginPage.clickOnLoginButton();

        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, "Invalid user ID", "Error text with empty input fields does not match");
    }

    @Test(priority = 2)
    public void testLoginWithEmptyUserId() {
        loginPage.loginToApplication("", "231225");

        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, "Invalid user ID", "Error text with empty User ID do not match");
    }

    @Test(priority = 3)
    public void testLoginWithEmptyPin() {
        loginPage.loginToApplication("142420", "");

        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, "Invalid PIN", "Error text with empty PIN do not match");
    }

    @Test(priority = 4)
    public void testLoginWithInvalidCreds() {
        loginPage.loginToApplication("142420", "123456");

        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(actualErrorMessage, "User ID and PIN didn't match", "Error text with invalid PIN do not match");
    }

    @Test(priority = 5)
    public void testLoginWithValidCreds() {
        loginPage.loginToApplication("142420", "231225");

        String expectedUrl = "https://qaebank.ccbp.tech/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "URLs do not match");
    }

}
