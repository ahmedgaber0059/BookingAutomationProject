package Pages.homePage;

import Pages.basePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomePage extends BasePage {

    By placeSelection = By.xpath("//input[@aria-label='Where are you going?']");
    By checkIn = By.xpath("//Button[@data-testid='date-display-field-start']");
    By checkOut = By.xpath("//Button[@data-testid='date-display-field-end']");
    By occupancyConfigButton = By.xpath("//button[@data-testid='occupancy-config']");

    By dateCell = By.xpath("//*[@role = 'gridcell']");
    By searchButton = By.cssSelector("button[type='submit']");

    By calenderNextArrow = By.xpath("//button[@aria-label='Next month']");

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public void selectCheckInAndCheckOutDate(String checkInDate, String checkOutDate) {
        By checkInDateLocator = By.xpath("//*[@data-date = '" + checkInDate + "']");
        By checkOutDateLocator = By.xpath("//*[@data-date = '" + checkOutDate + "']");

        CheckDatesInCalender(checkInDate);
        scrollToElement(checkInDateLocator);
        clickOnElement(checkInDateLocator);

        CheckDatesInCalender(checkOutDate);
        scrollToElement(checkOutDateLocator);
        clickOnElement(checkOutDateLocator);
    }

        public String getLastAvailableDate(){
        waitForVisibilityOfElement(dateCell);
        List<WebElement> dates = driver.findElements(dateCell);
        String latestDateDisplayedString = dates.get(dates.size() - 1).findElement(By.tagName("span")).getAttribute("data-date");

        return latestDateDisplayedString;
    }

    public boolean compareDate(String date1, String date2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2, formatter);

        return localDate1.isAfter(localDate2);
    }


    public void validateSearchElements(){
        validateElementIsDisplayed(placeSelection);
        validateElementIsDisplayed(checkIn);
        validateElementIsDisplayed(checkOut);
        validateElementIsDisplayed(occupancyConfigButton);
        validateElementIsDisplayed(searchButton);
    }

    public void sendInputsToSearchBar(String location, String subLocation){
        sendTextToField(placeSelection, location);
        clickOnElement(By.xpath("//div[normalize-space()='" + subLocation + "']"));
    }

    public void clickOnSearchButton(){
        clickOnElement(searchButton);
    }

    public void CheckDatesInCalender (String date){
        String lastAvailableDate = getLastAvailableDate();

        while (!compareDate(lastAvailableDate, date )){
             lastAvailableDate = getLastAvailableDate();
            clickOnElement(calenderNextArrow);
        }
    }
}
