package Tests;

import Pages.HomePage;
import Pages.LoginPage;
import Utility.propertyFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SendEmailTest {
    WebDriver driver;

    LoginPage objLogin;
   HomePage objHomePage;

    @BeforeTest

    public void setup() throws FileNotFoundException, IOException{

        WebDriverManager.chromedriver().version("110.0.5481.77").setup();
        driver = new ChromeDriver();
       // driver.get("https://account.proton.me/login");
        driver.get(propertyFileReader.readPropertyFile("URL", "./Data/Data.properties"));


    }
    @Test(priority=0)

    public void test_SignIn_Page_Appear_Correct() throws FileNotFoundException, IOException{

        //Create Login Page object

        objLogin = new LoginPage(driver);

        //Verify login page title
        WebDriverWait wait = new WebDriverWait(driver,100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Sign in']")));
        String loginPageTitle = objLogin.getLoginTitle();

        Assert.assertTrue(loginPageTitle.contains("Sign"));

        //login to application

        objLogin.loginToEmail(propertyFileReader.readPropertyFile("User", "./Data/Data.properties"),  propertyFileReader.readPropertyFile("Password", "./Data/Data.properties"));

        // go the next page

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Inbox']")));
        objHomePage = new HomePage(driver);

        //Verify home page

        Assert.assertTrue(objHomePage.getHomePageDetails().equals(propertyFileReader.readPropertyFile("HomePageLabel","./Data/Data.properties")));

        objHomePage.sendEmail(propertyFileReader.readPropertyFile("ToEmailAddress", "./Data/Data.properties"),propertyFileReader.readPropertyFile("Subject", "./Data/Data.properties"),propertyFileReader.readPropertyFile("EmailBody", "./Data/Data.properties"));





        driver.close();
        driver.quit();
    }


}
