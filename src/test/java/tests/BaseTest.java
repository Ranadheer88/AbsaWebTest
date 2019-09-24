package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.UserListPage;
import utils.Listners.TestListener;

@Listeners(TestListener.class)
public class BaseTest {
    public WebDriver driver;
    public UserListPage userListPage;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void classLevelSetup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://www.google.com/");
    }

    @BeforeMethod
    public void methodLevelSetup() {
        userListPage = new UserListPage(driver);
    }

    @AfterClass
    public void teardown() {
        //driver.quit();
    }

}
