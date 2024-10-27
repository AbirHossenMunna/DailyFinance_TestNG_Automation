package Pages.Admin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPageAdmin {
    @FindBy (id = "email")
    WebElement txtEmail;
    @FindBy (id = "password")
    WebElement txtPassword;
    @FindBy (css = "button[type='submit']")
    WebElement btnLogin;
    @FindBy(xpath = "//div[contains(text(),'Dashboard')]")
    public WebElement lblDashboardValidation;
    @FindBy(xpath = "//p[contains(text(),'Invalid email or password')]")
    WebElement lblErrorMessage;
    @FindBy (css = "button[type='button']")
    WebElement linkProfile;
    @FindBy (tagName = "li")
    List<WebElement> menuItem;
    @FindBy(tagName = "tr")
    public WebElement tblValidation;

    public LoginPageAdmin(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public String doLoginWithInvalidEmailAndValidPassword(String email, String password) throws InterruptedException {
        txtEmail.sendKeys(email);
        Thread.sleep(1000);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return lblErrorMessage.getText();
    }
    public String doLoginWithValidEmailAndInvalidPassword(String email, String password) throws InterruptedException {
        txtEmail.sendKeys(email);
        Thread.sleep(1000);
        txtPassword.sendKeys(password);
        btnLogin.click();
        return lblErrorMessage.getText();
    }
    public void doLoginWithValidCredential(String email, String password) throws InterruptedException {
        txtEmail.sendKeys(email);
        Thread.sleep(1000);
        txtPassword.sendKeys(password);
        btnLogin.click();
//        doLogout();
    }
    public void doLogout() throws InterruptedException {
        Thread.sleep(2000);
        linkProfile.click();
        Thread.sleep(2000);
        menuItem.get(1).sendKeys(Keys.ARROW_DOWN);
        menuItem.get(1).sendKeys(Keys.ENTER);
    }
    public void clearCredential (){
        txtEmail.sendKeys(Keys.CONTROL,"a");
        txtEmail.sendKeys(Keys.BACK_SPACE);
        txtPassword.sendKeys(Keys.CONTROL,"a");
        txtPassword.sendKeys(Keys.BACK_SPACE);
    }
}
