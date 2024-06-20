package Tests.baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.ExtentReport;
import utilities.ScreenShot;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void startDriver() {
        setBrowser("edge");
        navigateTo();
        ExtentReport.startReporting();
    }

    @BeforeMethod
    public void create (Method method){
        ExtentReport.createTest(method);
    }

    @AfterMethod
    public void aftermethodT(ITestResult result) throws IOException {
        ScreenShot.takeScreenShot(driver);
        ExtentReport.logStatus(result, driver);
    }

    private void navigateTo(){
        driver.get("https://www.booking.com/");
        driver.manage().window().maximize();
    }

    private void setBrowser(String browser){
        WebDriverManager.chromedriver().setup();
        switch (browser.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
            case"edge":
                driver = new EdgeDriver();
        }
    }

    @AfterClass
    public void teardown(){
        driver.quit();
        ExtentReport.flushReport();
    }

}
