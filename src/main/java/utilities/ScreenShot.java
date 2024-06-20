package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ScreenShot {

    public static void takeScreenShot(WebDriver driver) throws IOException {
        TakesScreenshot srcFile = (TakesScreenshot) driver;
        File source = srcFile.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source ,new File("D:\\IntelkWorkplace\\BookingAutomation\\src\\test\\java\\screenshot\\titletest.png"));


    }



    public static String captureScreenshot(String path, WebDriver driver) throws IOException {
        TakesScreenshot camera = (TakesScreenshot) driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);
        Path targetPath = Paths.get(path);
        Files.move(screenshot.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return path;
    }
}
