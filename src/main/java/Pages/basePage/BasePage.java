package Pages.basePage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
public class BasePage {
    public WebDriver driver;
    public By dismissOfferCloseButton = By.xpath("//*[@aria-label='Dismiss sign-in info.']");
    boolean popupFlag = true;


    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void dismissOfferPopupIfPresent(){
        try {
            waitForElementVisibilityForSeconds(dismissOfferCloseButton, 10);
            driver.findElement(dismissOfferCloseButton).click();
        }
        catch (Exception e){
            System.out.println("The popup for offers didn't appear");
        }
    }
    public void validateElementIsDisplayed(By by){
        waitForVisibilityOfElement(by);
        Assert.assertTrue(driver.findElement(by).isDisplayed());
    }

    public void clickOnElement(By by){
        waitForVisibilityOfElement(by);
        driver.findElement(by).click();
    }


    public void waitForVisibilityOfElement(By by){
        if (popupFlag){
            dismissOfferPopupIfPresent();
            popupFlag = false;
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void waitForElementVisibilityForSeconds (By by, int seconds){
        WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void sendTextToField (By by, String text){
        waitForVisibilityOfElement(by);
        clearField(by);
        driver.findElement(by).sendKeys(text);
    }

    public void scrollToElement(By by) {
        WebElement element = driver.findElement(by);

        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript(
                "const element = arguments[0];" +
                        "const rect = element.getBoundingClientRect();" +
                        "window.scrollTo({ " +
                        "    top: rect.top + window.scrollY - (window.innerHeight / 2)," +
                        "    behavior: 'smooth'" +
                        "});",
                element
        );
    }

    public void clearField(By by){
        driver.findElement(by).clear();
    }
}
