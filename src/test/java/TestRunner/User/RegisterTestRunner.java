package TestRunner.User;

import Base.Setup;
import Base.UserModel;
import Pages.User.RegisterPage;
import Utils.Utils;
import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;


public class RegisterTestRunner extends Setup {
    RegisterPage registerPage;
    Utils utils;
    String firstName;
    String lastName;
    String email;
    String password;
    String phoneNumber;
    String address;
    String duplicateEmail;
    public void basicInfo(){
        utils = new Utils();
        Faker faker = new Faker();
        firstName= faker.name().firstName();
        lastName = faker.name().lastName();
        email = "abir"+ utils.generateRandomNumber(000,999) + "@gmail.com";
        duplicateEmail ="abir4@gmail.com";
        password ="a1234567";
        phoneNumber ="017"+ utils.generateRandomNumber(00000000,99999999);
        address = faker.address().fullAddress();
    }
    @Test(priority = 0,description = "Registration with an already existing email")
    public void doRegistrationWithAlreadyExistsEmail() throws InterruptedException {
        registerPage = new RegisterPage(driver);
        registerPage.linkRegister.get(1).click();
        registerPage.checkBox.click();
        basicInfo();

        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(duplicateEmail);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        registerPage.doRegistration(userModel);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        String successfullyMsgActual =wait.until(ExpectedConditions.visibilityOf(registerPage.toastErrorMsg)).getText();
        String expected ="User with this email address already exists.";
        Assert.assertTrue(successfullyMsgActual.contains(expected));
        Allure.description("When a user attempts to register with an email that is already registered." +
                "Then system appropriate error message is shown and the registration is blocked.");
        Thread.sleep(3000);
        registerPage.clearCredential();
    }
    @Test(priority = 1,description = "Registration with mandatory fields only")
    public void userRegByMandatoryField() throws InterruptedException, IOException, ParseException{
        registerPage = new RegisterPage(driver);
        basicInfo();
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        registerPage.doRegistration(userModel);

        //Assertion
        doAssertion();

        JSONObject usersObj = new JSONObject();
        usersObj.put("firstName", firstName);
        usersObj.put("email", email);
        usersObj.put("phoneNumber", phoneNumber);
        usersObj.put("password", password);
        utils.saveJsonList("./src/test/resources/users.json",usersObj);

        Allure.description("This test verifies the registration process using valid details. " +
                "The test fills in required fields, submits the form, and checks for successful registration.");
    }
    @Test(priority = 3,description = "Register with valid data",groups = "smoke")
    public void userRegByAllField() throws InterruptedException, IOException, ParseException {
        registerPage = new RegisterPage(driver);
        registerPage.linkRegister.get(1).click();
        registerPage.checkBox.click();
        basicInfo();
        UserModel userModel = new UserModel();
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        registerPage.doRegistration(userModel);

        //Assertion
        doAssertion();

        JSONObject usersObj = new JSONObject();
        usersObj.put("firstName", firstName);
        usersObj.put("lastName", lastName);
        usersObj.put("email", email);
        usersObj.put("phoneNumber", phoneNumber);
        usersObj.put("password", password);
        usersObj.put("address", address);
        utils.saveJsonList("./src/test/resources/users.json",usersObj);

        Allure.description("This test verifies the registration process using valid details. " +
                "The test fills in required fields, submits the form, and checks for successful registration.");
    }
    public void doAssertion() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        String successfullyMsgActual =wait.until(ExpectedConditions.visibilityOf(registerPage.toastSuccessfullyMsg)).getText();
        String expected ="registered successfully!";
        Assert.assertTrue(successfullyMsgActual.contains(expected));
        String Actual = wait.until(ExpectedConditions.visibilityOf(registerPage.lblLogin)).getText();
        String msgExpected = "Login";
        Assert.assertTrue(Actual.contains(msgExpected));
    }
}
