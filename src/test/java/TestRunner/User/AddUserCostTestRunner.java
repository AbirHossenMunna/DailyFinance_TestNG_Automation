package TestRunner.User;

import Base.Setup;
import Pages.User.AddUserCostPage;
import Pages.User.UserLoginPage;
import Utils.Utils;
import io.qameta.allure.Allure;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddUserCostTestRunner extends Setup {
    AddUserCostPage addUserCostPage;
    UserLoginPage userLoginPage;
    Utils utils;
    String name;
    int amount;
    String purchaseDate;
    String month;
    String remarks;
    public void basicInfo(){
        name="Biryani";
        amount = 500;
        purchaseDate ="11-30";
        month="November";
        remarks ="User cost added as part of budget allocation for the project.";
    }

    @BeforeTest
    public void doLogin() throws IOException, ParseException, InterruptedException {
        userLoginPage = new UserLoginPage(driver);
        utils = new Utils();

        utils.getUserCreds();
        if (System.getProperty("username") != null && System.getProperty("password") != null) {
            userLoginPage.doLoginWithValidCredential(System.getProperty("username"), System.getProperty("password"));
        }
        else {
            userLoginPage.doLoginWithValidCredential(utils.getEmail(), utils.getPassword());
        }
    }
//    @Test(priority = 0,description = "User edit his/her image")
//    public void EditProfile() throws InterruptedException {
//        addUserCostPage = new AddUserCostPage(driver);
//        addUserCostPage.linkProfile.click();
//        Thread.sleep(2000);
//        addUserCostPage.menuItem.get(0).sendKeys(Keys.ARROW_DOWN);
//        addUserCostPage.menuItem.get(0).sendKeys(Keys.ENTER);
//        addUserCostPage.btnAction.get(1).click();
//        Thread.sleep(2000);
//        addUserCostPage.btnUploadImage.click();
//        Thread.sleep(2000);
//        addUserCostPage.EditProfile();
//        Allure.description("This test verifies that the user can successfully edit their profile details.");
//    }
    @Test(priority = 1, description = "Add Cost for an item")
    public void addCost() throws InterruptedException {
        addUserCostPage = new AddUserCostPage(driver);
//        driver.navigate().back();
        addUserCostPage.btnAdd.click();
        addUserCostPage.btnPlus.click();
        basicInfo();
        addUserCostPage.addCost(name,amount,purchaseDate,month,remarks);
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Allure.description("This test verifies the addition of costs to the cart.");
    }
    @Test(priority = 2, description = "Add Cost for an item")
    public void incorrectSearchItem() throws InterruptedException {
        addUserCostPage = new AddUserCostPage(driver);
        addUserCostPage.txtSearch.sendKeys("Item 1");

        String isGotTxtActual = addUserCostPage.lblNoData.getText();
        String expected = "No costs found.";
        Assert.assertTrue(isGotTxtActual.contains(expected));

        Allure.description("This test verifies the addition of costs to the cart.");
        addUserCostPage.clearCredential();
    }
    @Test(priority = 4, description = "Add Cost for an item")
    public void SearchItem() throws InterruptedException {
        addUserCostPage = new AddUserCostPage(driver);
        Thread.sleep(500);
        addUserCostPage.txtSearch.sendKeys("Biryani");

        String isGotTxtActual = addUserCostPage.tblValidation.getText();
        String expected = "500";
        Assert.assertTrue(isGotTxtActual.contains(expected));

        Allure.description("This test verifies the addition of costs to the cart.");
    }

}
