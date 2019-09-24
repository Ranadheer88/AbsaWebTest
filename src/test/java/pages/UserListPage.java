package pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.ExtentReports.ExtentTestManager;
import utils.ExtentReports.User;

public class UserListPage extends BasePage {

    public UserListPage(WebDriver driver) {
        super(driver);
    }

    String baseURL = "http://www.way2automation.com/angularjs-protractor/webtables/";
    By addUserButtonXpath = By.xpath("//*[@class='btn btn-link pull-right']");
    By fName = By.name("FirstName");
    By lName = By.name("LastName");
    By userName = By.name("UserName");
    By password = By.name("Password");
    By email = By.name("Email");
    By mobilephone = By.name("Mobilephone");
    By role = By.name("RoleId");
    By companyAAA = By.xpath("//*[@class='ng-pristine ng-valid']");
    By companyBBB = By.xpath("//*[@class='ng-valid ng-dirty']");
    By saveButton = By.xpath("//*[@class='btn btn-success']");
    By tableXpath = By.xpath("//*[@class='smart-table table table-striped']");
    
    public UserListPage openUserListPage(){
        driver.get(baseURL);
        return this;
    }

    public UserListPage verifyUserListPage(){
        waitVisibility(tableXpath);
        return this;
    }

    public UserListPage addUser(User user){

        click(addUserButtonXpath);
        clearForm();
        driver.findElement(fName).sendKeys(user.getfName());
        driver.findElement(lName).sendKeys(user.getLName());
        driver.findElement(password).sendKeys(user.getPassword());
        driver.findElement(email).sendKeys(user.getEmail());
        driver.findElement(mobilephone).sendKeys(user.getCell());
        WebElement testDropDown = driver.findElement(role);
        Select dropdown = new Select(testDropDown);
        dropdown.selectByIndex(user.getRole().contains("Admin") ? 2 : 1);

        driver.findElement(userName).sendKeys(user.getfName()+String.valueOf(Math.random()).substring(2,5));

        if(user.getCustomer().contains("AAA")){
            waitVisibility(companyAAA);
            driver.findElement(companyAAA).click();
        }else if(user.getCustomer().contains("BBB")) {
            waitVisibility(companyBBB);
            driver.findElement(companyBBB).click();
        }

        driver.findElement(saveButton).click();

        waitVisibility(tableXpath);



        return this;
    }

    public UserListPage verifyUserAdded(User user){
        WebElement table = driver.findElement(tableXpath);
        WebElement tableRow = table.findElement(By.xpath("//tbody/tr[1]"));
        WebElement cellIneed = tableRow.findElement(By.xpath("//tbody/tr[1]/td[1]"));
        ExtentTestManager.getTest().log(LogStatus.INFO,"First Name", cellIneed.getText());
        Assert.assertEquals(cellIneed.getText(),user.getfName());
        return this;
    }

    public void clearForm(){
        waitVisibility(saveButton);
        driver.findElement(fName).clear();
        driver.findElement(lName).clear();
        driver.findElement(password).clear();
        driver.findElement(email).clear();
        driver.findElement(mobilephone).clear();
        driver.findElement(userName).clear();
    }
}
