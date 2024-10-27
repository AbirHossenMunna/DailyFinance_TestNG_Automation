package Pages.User;

import Base.UserModel;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLoginPage {
    @FindBy(id = "email")
    WebElement txtEmail;
    @FindBy (id = "password")
    WebElement txtPassword;
    @FindBy (css = "button[type='submit']")
    WebElement btnLogin;
    @FindBy(xpath = "//div[contains(text(),'Dashboard')]")
    public WebElement lblDashboardValidation;
    @FindBy(xpath = "//p[contains(text(),'Invalid email or password')]")
    public WebElement lblErrorMessage;

    public UserLoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void doLoginWithValidCredential(String email, String password) throws InterruptedException {
        txtEmail.sendKeys(email);
        Thread.sleep(1000);
        txtPassword.sendKeys(password);
        btnLogin.click();
    }
    public void clearCredential() {
        txtEmail.sendKeys(Keys.CONTROL, "a");
        txtEmail.sendKeys(Keys.BACK_SPACE);
        txtPassword.sendKeys(Keys.CONTROL, "a");
        txtPassword.sendKeys(Keys.BACK_SPACE);
    }
}
