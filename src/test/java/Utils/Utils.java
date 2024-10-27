package Utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public void takeScreenshot(WebDriver driver) throws IOException {
        File screenshotFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time=new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath="./src/test/resources/screenshots/"+time+".png";
        File DestFile= new File(fileWithPath);
        FileUtils.copyFile(screenshotFile,DestFile);
    }
    private String email;
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String firstName;
    private String phoneNumber;
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //Read for Admin Credential. Read Json
    public void getAdminCreds(int pos) throws IOException, ParseException {
        String fileName="./src/test/resources/loginAdmin.json";
        JSONParser jsonParser = new JSONParser();
        Object obj =jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray=(JSONArray) obj;
        JSONObject jsonObject=(JSONObject) jsonArray.get(pos);
        setEmail((String)jsonObject.get("email"));
        setPassword((String)jsonObject.get("password"));
    }
    //Read for User Credential. Read Json
    public void getUserCreds() throws IOException, ParseException {
        String fileName="./src/test/resources/users.json";
        JSONParser jsonParser = new JSONParser();
        Object obj =jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray=(JSONArray) obj;
        JSONObject jsonObject=(JSONObject) jsonArray.get(jsonArray.size()-1);
        setEmail((String)jsonObject.get("email"));
        setPassword((String)jsonObject.get("password"));
    }
    public void getUserInfo() throws IOException, ParseException {
        String fileName="./src/test/resources/users.json";
        JSONParser jsonParser = new JSONParser();
        Object obj =jsonParser.parse(new FileReader(fileName));
        JSONArray jsonArray=(JSONArray) obj;
        JSONObject jsonObject=(JSONObject) jsonArray.get(jsonArray.size()-1);
        setFirstName((String)jsonObject.get("firstName"));
        setEmail((String)jsonObject.get("email"));
        setPhoneNumber((String)jsonObject.get("phoneNumber"));
    }
    // Putting data from Registration page . Write json
    public void saveJsonList(String fileName, JSONObject jsonObject)
            throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(fileName));
        JSONArray jArray = (JSONArray) obj;
        jArray.add(jsonObject);
        FileWriter file = new FileWriter(fileName);
        file.write(jArray.toJSONString());
        file.flush();
        file.close();
        System.out.println("Saved");
    }
    //getting user count from JSONArray. or ReadJson. use to LoginPage
//    public int getUserCount() throws IOException, ParseException {
//        String fileName="./src/test/resources/users.json";
//        JSONParser jsonParser=new JSONParser();
//        Object obj=jsonParser.parse(new FileReader(fileName));
//        JSONArray jsonArray=(JSONArray) obj;
//        return jsonArray.size()-1; //Always get tha last value
//    }
    public int generateRandomNumber(int min,int max){
        int randomId= (int)(Math.random() * ((max - min) + 1)) + min;
        return randomId;
    }
}
