package Base;

import TestRunner.User.AddNewCostCSVDataset;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddNewCostDataset {
    // Variable to store the total cost
    private static int totalCost = 0;
    @DataProvider(name = "AddNewCostCSVData")
    public Object[][] getCSVData() throws IOException {
        String filePath ="./src/test/resources/addCost.csv";
        List<Object[]> data = new ArrayList<>();
        CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT.withFirstRecordAsHeader());

        totalCost = 0;

        for (CSVRecord csvRecord:csvParser) {
            String itemName = csvRecord.get("itemName");
            int amount = Integer.parseInt(csvRecord.get("amount"));
            String purchaseDate = csvRecord.get("purchaseDate");
            String month = csvRecord.get("month");
            String remarks = csvRecord.get("remarks");
            // Add to total cost
            totalCost += amount;
            data.add(new Object[] {itemName,amount,purchaseDate,month,remarks});
        }
        return data.toArray(new Object[0][]);
    }
    // Static method to retrieve the dynamically calculated total cost
    public static int getTotalCost() {
        return totalCost;
    }
}

