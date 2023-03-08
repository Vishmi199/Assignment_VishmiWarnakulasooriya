package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;
    private static By txtUsername = By.id("username");
    private static By txtPassword = By.id("password");
    private static By btnSignin = By.xpath("//button[@type='submit']");
    private static By txtTitle = By.xpath("//h1[text()='Sign in']");

  //  private static By txtInvalidLogin = By.xpath("//div[@class='portal-alert-header--error']");

    public LoginPage(WebDriver driver){

        this.driver = driver;

    }
    //Set user name in textbox

    public void setUserName(String strUserName){

        driver.findElement(txtUsername).sendKeys(strUserName);

    }

    //Set password in password textbox

    public void setPassword(String strPassword){

        driver.findElement(txtPassword).sendKeys(strPassword);

    }
//Click on Signin button

    public void clickLogin(){

        driver.findElement(btnSignin).click();

    }

    //Get the title of Login Page

    public String getLoginTitle(){

        return    driver.findElement(txtTitle).getText();

    }
    public void loginToEmail(String strUserName,String strPasword){

        //Fill user name

        this.setUserName(strUserName);

        //Fill password

        this.setPassword(strPasword);

        //Click Login button

        this.clickLogin();
    }



}
