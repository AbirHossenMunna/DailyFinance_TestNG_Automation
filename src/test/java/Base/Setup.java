package Base;

import Utils.Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

public class Setup {
    public WebDriver driver;

    @BeforeTest(groups = "smoke")
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
        driver.get("https://dailyfinance.roadtocareer.net/");
    }
    @AfterMethod
    public void screenShot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                Utils utils = new Utils();
                utils.takeScreenshot(driver);
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
    }

    @AfterTest(groups = "smoke")
    public void closeBrowser() {
        driver.close();
    }
}
