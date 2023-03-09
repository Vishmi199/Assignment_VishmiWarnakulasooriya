package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import Pages.LogoutPage;
import Utility.propertyFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SendEmailTest {
    WebDriver driver;
    LoginPage objLogin;
    HomePage objHomePage;
    LogoutPage objLogoutPage;

    @BeforeTest
    public void setup() throws InterruptedException{
        WebDriverManager.chromedriver().version("110.0.5481.77").setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(propertyFileReader.readPropertyFile("URL", "./Data/Data.properties"));
    }

    @Test(priority=0)
    public void test_SignIn_Page_Appear_Correct() throws InterruptedException {

        //Create Login Page object
        objLogin = new LoginPage(driver);

        //Verify login page title
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        String loginPageTitle = objLogin.getLoginTitle();
        //Verify that the loginPage is loaded successfully.
        Assert.assertTrue(loginPageTitle.contains("Sign"));

        //login to application
        objLogin.loginToEmail(propertyFileReader.readPropertyFile("User", "./Data/Data.properties"), propertyFileReader.readPropertyFile("Password", "./Data/Data.properties"));

        objHomePage = new HomePage(driver);
        //Verify home page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Inbox']")));
        Assert.assertTrue(objHomePage.getHomePageDetails().equals(propertyFileReader.readPropertyFile("HomePageLabel", "./Data/Data.properties")));

        //Send an email to the email address two
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='New message']")));
        objHomePage.sendEmail(propertyFileReader.readPropertyFile("ToEmailAddress", "./Data/Data.properties"), propertyFileReader.readPropertyFile("Subject", "./Data/Data.properties"), propertyFileReader.readPropertyFile("EmailBody", "./Data/Data.properties"));

        //Logout from Email address one
        objLogoutPage = new LogoutPage(driver);
        Thread.sleep(10000);
        objLogoutPage.logOut();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        //Login to Email address two
        objLogin.getLoginTitle();
        Assert.assertTrue(loginPageTitle.contains("Sign"));
        objLogin.loginToEmail(propertyFileReader.readPropertyFile("User2", "./Data/Data.properties"), propertyFileReader.readPropertyFile("Password2", "./Data/Data.properties"));

        //Verify Email Address two home page
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='New message']")));
        Assert.assertTrue(objHomePage.getHomePageDetails().equals(propertyFileReader.readPropertyFile("HomePageLabel", "./Data/Data.properties")));


        //Verify received email details.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@role='heading']")));
        objHomePage.openReceivedEmail();
        Assert.assertTrue(objHomePage.getToEmailAddress().equals(propertyFileReader.readPropertyFile("User2", "./Data/Data.properties")));
        Assert.assertTrue(objHomePage.getReceivedSubject().contains(propertyFileReader.readPropertyFile("Subject", "./Data/Data.properties")));
        Assert.assertTrue(objHomePage.getSenderEmail().contains(propertyFileReader.readPropertyFile("User", "./Data/Data.properties")));
        Assert.assertTrue(objHomePage.getEmailBody().equals(propertyFileReader.readPropertyFile("EmailBody", "./Data/Data.properties")));
        driver.switchTo(). defaultContent();

        //Delete the received emails
       objHomePage.deleteEmails();
    }

    @AfterTest
    public void Close() {
        //Close the browser
        driver.close();
        driver.quit();
    }

}
