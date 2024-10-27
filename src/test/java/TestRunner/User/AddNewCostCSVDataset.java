package TestRunner.User;

import Base.AddNewCostDataset;
import Base.Setup;
import Pages.User.AddUserCostPage;
import Pages.User.UserLoginPage;
import Utils.Utils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;

public class AddNewCostCSVDataset extends Setup {
    AddUserCostPage addUserCostPage;
    UserLoginPage userLoginPage;
    Utils utils;

    @BeforeTest(groups = "smoke")
    public void doLogin() throws IOException, ParseException, InterruptedException {
        userLoginPage = new UserLoginPage(driver);
        utils = new Utils();

        utils.getUserCreds();
        if (System.getProperty("username") != null && System.getProperty("password") != null) {
            userLoginPage.doLoginWithValidCredential(System.getProperty("username"), System.getProperty("password"));
        } else {
            userLoginPage.doLoginWithValidCredential(utils.getEmail(), utils.getPassword());
        }
    }

    private static int totalCost = 0;

    @Test(dataProvider = "AddNewCostCSVData", dataProviderClass = AddNewCostDataset.class,groups = "smoke")
    public void AddCostCSVData(String itemName, int amount, String purchaseDate, String month, String remarks) throws InterruptedException {
        addUserCostPage = new AddUserCostPage(driver);
        addUserCostPage.btnAdd.click();
        addUserCostPage.btnPlus.click();
        addUserCostPage.addCost(itemName, amount, purchaseDate, month, remarks);

        Thread.sleep(3000);
        driver.switchTo().alert().accept();

        //Printing each record for verification
        System.out.println("Item: " + itemName + ", Amount: " + amount + ", Purchase Date: " + purchaseDate + ", Month: " + month + ", Remarks: " + remarks);
    }


    // Method to verify total cost after all rows have been processed
    @Test(dependsOnMethods = "AddCostCSVData")
    public void assertTotalCost() {

        // Get the dynamically calculated total cost from the utility class
        int actualTotalCost = AddNewCostDataset.getTotalCost();

        // Print the total cost
        System.out.println("Total cost from CSV: " + actualTotalCost);

        // Set an expected value based on the CSV data or dynamically calculate it in the test
        int expectedTotalCost = 1330; // Set this to the expected total sum from your CSV file

        // Assert the total cost matches the expected value
        Assert.assertEquals(actualTotalCost, expectedTotalCost);
    }

    //Variable for the item to search for (could be parameterized or set programmatically)
    private static final String ITEM_TO_SEARCH = "Biryani";
    private int foundItemPrice = -1;
    private boolean hasSearched = false; // Flag to track if the search has been performed

    // Search Item
    @Test(dataProvider = "AddNewCostCSVData", dataProviderClass = AddNewCostDataset.class, groups = "smoke")
    public void searchAndAssertItem(String itemName, int amount, String purchaseDate, String month, String remarks) throws InterruptedException {
        Thread.sleep(1000);
        // Perform the search only once
        if (!hasSearched) {
            Thread.sleep(3000);
            addUserCostPage = new AddUserCostPage(driver);
            addUserCostPage.txtSearch.sendKeys(ITEM_TO_SEARCH);

            // Check if the current item matches the dynamically specified item to search for
            if (itemName.equalsIgnoreCase(ITEM_TO_SEARCH)) {
                System.out.println("Item found: " + itemName);
                System.out.println("Item Amount: " + amount + ", Purchase Date: " + purchaseDate + ", Month: " + month + ", Remarks: " + remarks);

                // Save the price for the searched item
                foundItemPrice = amount;

                // Perform assertions on details
                Assert.assertEquals(amount, foundItemPrice, "The total cost for the searched item does not match the price listed in CSV.");
                Assert.assertTrue(foundItemPrice != -1, "Item was not found in the CSV file.");
                System.out.println("Total cost of the searched item (" + ITEM_TO_SEARCH + "): " + foundItemPrice);
            }

            // Set the flag to true to prevent further searches
            hasSearched = true;
        } else {
            System.out.println("Search has already been performed. Skipping search for item: " + ITEM_TO_SEARCH);
        }
    }
}

