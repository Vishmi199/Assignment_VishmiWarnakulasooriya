package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {
    WebDriver driver;
    private static By lnkdropDown = By.xpath("//button[contains(@title,'assemail')]");
    private static By btnSignout = By.xpath("//button[text()='Sign out']");


    public LogoutPage(WebDriver driver){
        this.driver = driver;
    }

    //Click the dropdown
    public void clickDropDown(){
        driver.findElement(lnkdropDown).click();
    }

    //Click on Logout button
    public void clickLogout(){
        driver.findElement(btnSignout).click();
    }

        public void logOut(){

        //Click Drop Down
            this.clickDropDown();
        //Click Login button

        this.clickLogout();
    }



}
