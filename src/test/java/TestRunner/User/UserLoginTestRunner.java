package TestRunner.User;

import Base.Setup;
import Pages.User.UserLoginPage;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class UserLoginTestRunner extends Setup {
    UserLoginPage userLoginPage;
    Utils utils;
    String invalidEmail;
    String invalidPassword;
    public void basicInfo(){
        invalidEmail ="abir@gmail";
        invalidPassword ="12345";
    }
    @Test(priority = 0, description = "Users tries to login with incorrect email but correct password")
    public void doLoginWithInvalidEmailAndValidPassword () throws IOException, ParseException, InterruptedException {
        userLoginPage = new UserLoginPage(driver);
        utils = new Utils();
        basicInfo();
        utils.getUserCreds();
        userLoginPage.doLoginWithValidCredential(invalidEmail,utils.getPassword());

        //Assertion
        doAssertion();

        Allure.description("User tries to login with Invalid username and correct password" +
                "User will not be allowed to login and 'Invalid email or password' will be prompted");
        userLoginPage.clearCredential();
    }
    @Test(priority = 1, description = "Users tries to login with correct email but incorrect password")
    public void doLoginWithValidEmailAndInvalidPassword () throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        userLoginPage.clearCredential();
        Thread.sleep(3000);
        userLoginPage = new UserLoginPage(driver);
        utils = new Utils();
        basicInfo();
        utils.getUserCreds();
        userLoginPage.doLoginWithValidCredential(utils.getEmail(), invalidPassword);

        //Assertion
        doAssertion();

        Allure.description("User tries to login with Invalid username and correct password" +
                "User will not be allowed to login and 'Invalid email or password' will be prompted");
        Thread.sleep(3000);
        userLoginPage.clearCredential();
    }
    @Test(priority = 2, description = "Admin gives valid credentials and login is successful",groups = "smoke")
    public void doLoginWithValidCredential () throws IOException, ParseException, InterruptedException {
        Thread.sleep(3000);
        userLoginPage = new UserLoginPage(driver);
        utils = new Utils();

        utils.getUserCreds();
        userLoginPage.doLoginWithValidCredential(utils.getEmail(),utils.getPassword());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        String successfullyMsgActual =wait.until(ExpectedConditions.visibilityOf(userLoginPage.lblDashboardValidation)).getText();
        String expectedTxt = "Dashboard";
        Assert.assertTrue(successfullyMsgActual.contains(expectedTxt));
        String actual = driver.findElement(By.tagName("h2")).getText();
        String expected = "User Daily Costs";
        Assert.assertTrue(actual.contains(expected));
        Allure.description("This test verifies that a user can log in with valid credentials and" +
                " is successfully redirected to the dashboard.");
    }
    public void doAssertion() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        String errorMsgActual =wait.until(ExpectedConditions.visibilityOf(userLoginPage.lblErrorMessage)).getText();
        String expectedTxt = "Invalid email or password";
        Assert.assertTrue(errorMsgActual.contains(expectedTxt));
    }
}
