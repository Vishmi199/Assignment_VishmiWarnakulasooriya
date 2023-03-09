package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;

    private static By homePageLabel= By.xpath("//span[text()='Inbox']");
    private static By newMessageBtn= By.xpath("//button[text()='New message']");
    private static By addToEmailAddress= By.xpath("//label[text()='Email address']/following-sibling::input");
    private static By addSubject= By.xpath("//input[@placeholder='Subject']");
    private static By addEmailBody= By.xpath("//div[@id='proton-editor-container']//div//div//div");
    private static By btnSend = By.xpath("//button[@class='button button-group-item button-solid-norm composer-send-button']");
    private static By txtSuccessMsg = By.xpath("//span[@class='notification__content']/span/text()");
    //Read received email details
    private static By txtRecipientEmail = By.xpath("//div[@id='message-recipients']//span[@class='message-recipient-item-label']");
    private static By txtSenderEmail = By.xpath("(//span[@class='message-recipient-item-label'])/following-sibling::span");
    private static By txtReceivedEmailBody = By.xpath("//*[@id='proton-root']/div[2]/div/div");
    private static By txtReceivedSubject = By.xpath(" //h1/span");
    private static By lblEmailHeading = By.xpath("//span[@role='heading']");
    //Delete emails
    private static By btnMoveToTrash = By.xpath("(//span[text()='Move to trash']/ancestor::button)");
    private static By lblMore = By.xpath("//button[@title='More']");
    private static By lblTrashBin = By.xpath("//span[text()='Trash']");
    private static By selectAll = By.xpath("//input[@id='idSelectAll']");
    private static By btnPermanentDelete =By.xpath("(//span[text()='Delete permanently']/ancestor::button)");
    private static By btnDelete =By.xpath("//button[text()='Delete']");


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //Get the text from Home Page
    public String getHomePageDetails(){
        return driver.findElement(homePageLabel).getText();
    }
    public void clickInbox(){
        driver.findElement(homePageLabel).click();
    }
    //Click New Message button
    public void clickNewEmailBtn(){
        driver.findElement(newMessageBtn).click();
    }
    //Set TO address
    public void setToEmailAddress(String strToEmail){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToEmailAddress));
        driver.findElement(addToEmailAddress).sendKeys(strToEmail);
    }
    //Get TO address
    public String getToEmailAddress(){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtRecipientEmail));
        return    driver.findElement(txtRecipientEmail).getText();
    }
    //Set Subject
    public void setEmailSubject(String strSubject){
        driver.findElement(addSubject).sendKeys(strSubject);
    }
    //Get received Subject
    public String getReceivedSubject(){
        return driver.findElement(txtReceivedSubject).getText();
    }
    //Get Sender Email
    public String getSenderEmail(){
        return driver.findElement(txtSenderEmail).getText();
    }
    //Set email body
    public void setEmailBody(String strEmailBody){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Email composer']")));
        driver.findElement(addEmailBody).sendKeys(strEmailBody);
        driver.switchTo(). defaultContent();
    }
    //Get email body
    public String getEmailBody(){
        WebDriverWait wait = new WebDriverWait(driver, 100);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Email content']")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(txtReceivedEmailBody));
        return driver.findElement(txtReceivedEmailBody).getText();
    }
    //Click on Sign in button
    public void clickSendBtn(){
        driver.findElement(btnSend).click();
    }
    //Get Success message
    public String getSuccessMessage(){
        return    driver.findElement(txtSuccessMsg).getText();
    }
    public void clickReceivedEmailHeading(){
        driver.findElement(lblEmailHeading).click();
    }

    //Delete the message
    public void deleteEmail() {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        driver.findElement(btnMoveToTrash).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblMore));
        driver.findElement(lblMore).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(lblTrashBin));
        driver.findElement(lblTrashBin).click();
       // wait.until(ExpectedConditions.visibilityOfElementLocated(selectAll));
        driver.findElement(selectAll).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnPermanentDelete));
        driver.findElement(btnPermanentDelete).click();
        driver.findElement(btnDelete).click();

    }

    public void sendEmail(String strToEmail,String strSubject,String strEmailBody){
        //click new Message button
        this.clickNewEmailBtn();
        //Fill To email address
        this.setToEmailAddress(strToEmail);
        //Fill Subject
        this.setEmailSubject(strSubject);
        //Fill email body
        this.setEmailBody(strEmailBody);
        //Click Login button
        this.clickSendBtn();
    }

    public void openReceivedEmail(){
        //refresh the Inbox
        this.clickInbox();
        //Open received email
        this.clickReceivedEmailHeading();
   }
    public void deleteEmails(){
        this.deleteEmail();
    }
}
