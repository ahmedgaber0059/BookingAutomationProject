package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {

    public static void takeScreenShot(WebDriver driver, ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String baseDir = System.getProperty("user.dir") + "\\attachments\\";
            String methodName = result.getMethod().getMethodName();
            createDirectory(baseDir + "screenshotsFailure");

            TakesScreenshot srcFile = (TakesScreenshot) driver;
            File source = srcFile.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Path targetPath = Paths.get(baseDir  +"screenshotsFailure\\" + methodName +  timestamp + ".png");
            Files.move(source.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

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


    public static String captureScreenshot(String path, WebDriver driver) throws IOException {
        TakesScreenshot camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        Path targetPath = Paths.get(path);
        Files.move(screenshot.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return path;
    }
}
