package TestRunner.Admin;

import Base.AdminLoginDataset;
import Base.Setup;
import Pages.Admin.LoginPageAdmin;
import org.testng.annotations.Test;

public class LoginCSVDataSet extends Setup {
    @Test(dataProvider = "AdminLoginCSVData", dataProviderClass = AdminLoginDataset.class)
    public void doLogin(String email, String password) throws InterruptedException {
        LoginPageAdmin loginPage = new LoginPageAdmin(driver);
        loginPage.doLoginWithValidCredential(email,password);
    }
}
