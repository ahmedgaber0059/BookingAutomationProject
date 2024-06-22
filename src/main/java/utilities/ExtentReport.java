package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReport {

    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentSparkReporter sparkReporter;

    public static ExtentHtmlReporter htmlReporter;


    public static void startReporting()  {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String reportName = System.getProperty("user.dir") + "\\attachments\\extentReports\\extentReport_" + timestamp + ".html";
        sparkReporter = new ExtentSparkReporter(reportName);
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static void createTest(Method method){
        test = extent.createTest(method.getName());
    }

    public static void logStatus(ITestResult result, WebDriver driver) {

        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test failed");
            Throwable throwable = result.getThrowable();
            test.fail(throwable);

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped");
        }

    }



/*    public static void logStatus(ITestResult result, WebDriver driver) throws IOException {
        String baseDir = System.getProperty("user.dir") + "//reports//";
        String pathFail = baseDir + "screenshotsFailure//" + result.getName() + ".png";
        String PathSkip = baseDir + "screenshotsSkip//" + result.getName() + ".png";
        String pathSuccess = baseDir + "screenshotsSuccess//" + result.getName() + ".png";

        // Create directories if they don't exist
        createDirectory(baseDir + "screenshotsFailure");
        createDirectory(baseDir + "screenshotsSkip");
        createDirectory(baseDir + "screenshotsSuccess");



        if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed", MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.captureScreenshot(pathSuccess, driver)).build());
        } else if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.captureScreenshot(pathFail, driver)).build());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped", MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.captureScreenshot(PathSkip, driver)).build());
        }
    }*/
    private static void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + path);
            } else {
                System.err.println("Failed to create directory: " + path);
            }
        }
    }
    public static void flushReport(){
        extent.flush();
    }


}
