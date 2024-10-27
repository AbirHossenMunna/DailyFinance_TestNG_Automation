package Pages.User;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AddUserCostPage {
    @FindBy(css = ".add-cost-button")
    public WebElement btnAdd;
    @FindBy(id = "itemName")
    WebElement txtName;
    @FindBy(xpath = "//button[contains(text(),'+')]")
    public WebElement btnPlus;
    @FindBy(xpath = "//button[contains(text(),'-')]")
    public WebElement btnMinus;
    @FindBy(id = "amount")
    WebElement txtAmount;
    @FindBy(id = "purchaseDate")
    WebElement calenderDate;
    @FindBy(id = "month")
    public WebElement selectMonth;
    @FindBy(id = "remarks")
    public WebElement txtRemarks;
    @FindBy(tagName = "button")
    List<WebElement> btnAll;
    @FindBy (css = "button[type='button']")
    public WebElement linkProfile;
    @FindBy (tagName = "li")
    public List<WebElement> menuItem;
    @FindBy(tagName = "button")
    public List<WebElement> btnAction;
    @FindBy(className = "upload-input")
    public WebElement uploadFile;
    @FindBy(tagName = "button")
    List<WebElement> btnUploadImage;
    @FindBy(css = ".search-input")
    public WebElement txtSearch;
    @FindBy(className = "no-data")
    public WebElement lblNoData;
    @FindBy(xpath = "//tbody/tr/td[3]")
    public WebElement tblValidation;
    WebDriver driver;
    public AddUserCostPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public void addCost(String itemName, int amount,String purchaseDate,String month,String remarks) throws InterruptedException {
        txtName.sendKeys(itemName);
        txtAmount.sendKeys(String.valueOf(amount));
        Thread.sleep(2000);
        calenderDate.sendKeys(purchaseDate);
        calenderDate.sendKeys(Keys.ARROW_RIGHT);
        Thread.sleep(2000);
        selectMonth.sendKeys(month);
        txtRemarks.sendKeys(remarks);
        btnAll.get(3).click();
    }
//    public void EditProfile() throws InterruptedException {
//        uploadFile.sendKeys("D:\\job\\Signeture& Image\\PICTOR.jpg");
//        btnUploadImage.get(1).click();
//        driver.switchTo().alert().accept();
//        btnUpload.get(2).click();
//        driver.switchTo().alert().accept();
//    }
    public void clearCredential() {
        txtSearch.sendKeys(Keys.CONTROL, "a");
        txtSearch.sendKeys(Keys.BACK_SPACE);
    }
}
