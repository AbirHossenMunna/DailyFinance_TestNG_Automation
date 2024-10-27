package Pages.User;

import Base.UserModel;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.time.Duration;
import java.util.List;

public class RegisterPage {
    @FindBy(tagName = "a")
    public List<WebElement> linkRegister;
    @FindBy(id = "firstName")
    WebElement txtFirstName;
    @FindBy(id = "lastName")
    WebElement txtLastName;
    @FindBy(id = "email")
    WebElement txtEmail;
    @FindBy(id = "password")
    WebElement txtPassword;
    @FindBy(id = "phoneNumber")
    WebElement txtPhoneNumber;
    @FindBy(id = "address")
    WebElement txtAddress;
    @FindBy(name = "gender")
    List<WebElement> radioGender; //0,1
    @FindBy(css = "input[type='checkbox']")
    public WebElement checkBox;
    @FindBy(id = "register")
    WebElement btnRegister;
    @FindBy(xpath = "//div[contains(text(),'registered successfully!')]")
    public WebElement toastSuccessfullyMsg;
    @FindBy(xpath = "//div[contains(text(),'User with this email address already exists.')]")
    public WebElement toastErrorMsg;
    @FindBy(xpath = "//h1[contains(text(),'Login')]")
    public WebElement lblLogin;

    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void doRegistration(UserModel userModel) throws InterruptedException {
        txtFirstName.sendKeys(userModel.getFirstName());
        txtLastName.sendKeys(userModel.getLastName() != null ? userModel.getLastName() : "");
        txtEmail.sendKeys(userModel.getEmail());
        txtPassword.sendKeys(userModel.getPassword());
        txtPhoneNumber.sendKeys(userModel.getPhoneNumber());
        txtAddress.sendKeys(userModel.getAddress() != null ? userModel.getAddress() : "");
        radioGender.get(0).click();
        btnRegister.click();
    }
    public void clearCredential() {
        txtFirstName.sendKeys(Keys.CONTROL, "a");
        txtFirstName.sendKeys(Keys.BACK_SPACE);
        txtLastName.sendKeys(Keys.CONTROL, "a");
        txtLastName.sendKeys(Keys.BACK_SPACE);
        txtEmail.sendKeys(Keys.CONTROL, "a");
        txtEmail.sendKeys(Keys.BACK_SPACE);
        txtPassword.sendKeys(Keys.CONTROL, "a");
        txtPassword.sendKeys(Keys.BACK_SPACE);
        txtPhoneNumber.sendKeys(Keys.CONTROL, "a");
        txtPhoneNumber.sendKeys(Keys.BACK_SPACE);
        txtAddress.sendKeys(Keys.CONTROL, "a");
        txtAddress.sendKeys(Keys.BACK_SPACE);
    }

}
