# DailyFinance_TestNG_Automation

### This is a complete project where a DailyFinance site is automated by writing test suites using selenium-webdriver and TestNg as testing framework.
The following key modules/pages are automated:

* Register User implementing different Scenarios
* Log in as admin (pass admin credentials from the terminal) and check if the last registered user is displayed on the admin dashboard. 
* Log in with the last registered user and update their profile image.
* Add a cost/expenditure from a CSV file. Create a CSV file with 5 rows, This test will loop 5 times, as there are 5 data sets in the CSV.
* Print the total cost and assert it against your expected total sum of the amounts.
* Search for an item by name from the list and assert that the total cost matches the item's price.
* Create Regression Suite and Smoke Suite, ANd run them individually.

For failed test cases it will take a screenshot aswell at the point of failure.

### Technology:

* Tool: Selenium Webdriver
* IDE: Intellij
* Build tool: Gradle
* Language: Java
* Test_Runner: TestNG

### Prerequisite:
* Need to install jdk 11, gradle and allure
* Configure Environment variable for jdk 11, gradle and allure
* Clone this project and unzip it
* Open the project folder
* Double click on "build.gradle" and open it through IntellIJ IDEA
* Let the project build successfully
* Click on "Terminal" and run the automation scripts

### Run the Automation Script by the following command:
* Selenium will open the browser and start automating for smokeSuites.
  
```bash
  ./gradlew test -PsuiteName="smokeSuites.xml" 
```
### Testing Report for SmokeSuites
#### Here is the Summary report:
![Test Summary (Smoke)](https://github.com/user-attachments/assets/b9ae8cfd-41f5-4537-9486-d7c8e6124c6f)

#### Here is the Allure report overview:
![Allure Report(Smoke)](https://github.com/user-attachments/assets/2faa4606-d898-4c2a-8538-a4df69d523ac)

#### Here are the allure suites of this project:
![Allure Suites(smoke)](https://github.com/user-attachments/assets/42053b27-75a0-4365-bb21-4451c9b96641)

#### Here are the Allure Behaviors of this project:
![Allure_Behaviour (Smoke)](https://github.com/user-attachments/assets/0d60223a-5a5d-42c5-a921-f3d735cb0701)

### Run the Automation Script by the following command:
* Selenium will open the browser and start automating for regressionSuites.
  
```bash
  ./gradlew test -PsuiteName="regressionSuites.xml"
```

* After automation to view allure report , give the following commands:

```bash
allure generate allure-results --clean -o allure-report
allure serve allure-results
```
### Testing Report for RegressionSuites
#### Here is the Summary report:
![Test Summary (Regression)](https://github.com/user-attachments/assets/8795106e-03d6-4971-932a-5c3349f96e2b)

#### Here is the Allure report overview:
![Allure Report (Regression)](https://github.com/user-attachments/assets/b42fbdf4-94e1-4530-b3ed-82ffa8130f0b)

#### Here are the allure suites of this project:
![Allure Suites  (Regression)](https://github.com/user-attachments/assets/6ae22fbf-1e13-4add-b7ae-be9ff33217e6)

#### Here are the Allure Behaviors of this project:
![Allure Behaviour (Regression)](https://github.com/user-attachments/assets/877dbbe6-9638-44f5-862b-86bc0436bf9f)

