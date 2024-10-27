package TestRunner.Admin;

import Base.Setup;
import Pages.Admin.LoginPageAdmin;
import Utils.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AdminLoginTestRunner extends Setup {
    LoginPageAdmin loginPage;
    Utils utils;

    @Test(priority = 0, description = "Admin tries to login with incorrect email but correct password")
    public void doLoginWithInvalidEmailAndValidPassword() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPageAdmin(driver);
        utils = new Utils();
        utils.getAdminCreds(0);
        String isGotErrorMsg = loginPage.doLoginWithInvalidEmailAndValidPassword(utils.getEmail(), utils.getPassword());
        String expectedTxt = "Invalid email or password";
        Assert.assertTrue(isGotErrorMsg.contains(expectedTxt));
        Allure.description("User tries to login with Invalid username and correct password" +
                "User will not be allowed to login and 'Invalid email or password' will be prompted");
        loginPage.clearCredential();
    }

    @Test(priority = 1, description = "Admin tries to login with correct email but incorrect password")
    public void doLoginWithValidEmailAndInvalidPassword() throws IOException, ParseException, InterruptedException {
        loginPage = new LoginPageAdmin(driver);
        utils = new Utils();
        utils.getAdminCreds(1);
        String isGotErrorMsg = loginPage.doLoginWithValidEmailAndInvalidPassword(utils.getEmail(), utils.getPassword());
        String expectedTxt = "Invalid email or password";
        Assert.assertTrue(isGotErrorMsg.contains(expectedTxt));
        Allure.description("User tries to login with valid email and Incorrect password" +
                "User will not be allowed to login and 'Invalid email or password' will be prompted");
        loginPage.clearCredential();
    }

    @Test(priority = 2, description = "Admin gives valid credentials and login is successful")
    public void doLoginWithValidCredential() throws Exception {
        loginPage = new LoginPageAdmin(driver);
        utils = new Utils();
        utils.getAdminCreds(2);
        loginPage.doLoginWithValidCredential(utils.getEmail(), utils.getPassword());
        Thread.sleep(3000);
        String isGotText = loginPage.lblDashboardValidation.getText();
        String expectedTxt = "Dashboard";
        Assert.assertTrue(isGotText.contains(expectedTxt));

        String actual = driver.findElement(By.tagName("h2")).getText();
        String expected = "Admin Dashboard";
        Assert.assertTrue(actual.contains(expected));

        utils.getUserInfo();
        System.out.println("First name is: " + utils.getFirstName() + "\n" +
                "Email: " + utils.getEmail() + "\n" +
                "Phone number: " + utils.getPhoneNumber());
        Thread.sleep(2000);
        assertJsonData();

        Thread.sleep(2000);
        loginPage.doLogout();
        Allure.description("This test verifies that a user can log in with valid credentials and" +
                " is successfully redirected to the dashboard.");
    }

    public void assertJsonData() throws Exception {
        // Path to your JSON file
        String fileName = "./src/test/resources/users.json";

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialize JSON array into a List of Maps
        List<Map<String, Object>> jsonArray = objectMapper.readValue(new File(fileName), List.class);

        // Access the first element of the array
        Map<String, Object> jsonMap = jsonArray.get(jsonArray.size()-1);

        // Extract values from the parsed JSON
        String actualFirstName = (String) jsonMap.get("firstName");
        String actualEmail = (String) jsonMap.get("email");
        String actualPhoneNumber = (String) jsonMap.get("phoneNumber");

        // Perform assertions
        Assert.assertEquals(actualFirstName, "Jules", "Name doesn't match");
        Assert.assertEquals(actualEmail, "abir754@gmail.com", "Email doesn't match");
        Assert.assertEquals(actualPhoneNumber, "01755855252", "Phone number doesn't match");
    }
}

