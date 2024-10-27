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
<img width="935" alt="Test Summary (Smoke)" src="https://github.com/user-attachments/assets/4b0a02e8-142f-451c-90e7-36cb1cab21bb.PNG">

#### Here is the Allure report overview:
<img width="935" alt="Allure Report(Smoke)" src="https://github.com/user-attachments/assets/26b4139c-675b-4e88-85e7-f5442e5c8356.PNG">

#### Here are the suites of this project:
<img width="920" alt="Allure Suites(smoke)" src="https://github.com/user-attachments/assets/937d2ed3-c6ac-45f8-afca-94fd21134957.PNG">

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
<img width="934" alt="Test Summary (Regression)" src="hhttps://github.com/user-attachments/assets/91a1da93-279e-4f84-8aa1-743bc10d83f9.PNG">
![Allure Suites  (Regression)](https://github.com/user-attachments/assets/f9f0e604-3b7c-4031-868f-3f63e166c9f1)



#### Here is the Allure report overview:
<img width="935" alt="Allure Report (Regression)" src="https://github.com/user-attachments/assets/4fa2b025-9271-47d2-8533-ad6370119fe0.PNG">

#### Here are the suites of this project:
<img width="920" alt="Allure Suites  (Regression)" src="https://github.com/user-attachments/assets/f9f0e604-3b7c-4031-868f-3f63e166c9f1.PNG">
