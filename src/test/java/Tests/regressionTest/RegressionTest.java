package Tests.regressionTest;

import Pages.homePage.HomePage;
import Tests.baseTest.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class RegressionTest extends BaseTest {
    HomePage homePage;

    @BeforeClass
    public void initializeObjects(){
         homePage = new HomePage(driver);
    }

    @Test
    public void
    validateSearchFunctionality(){
        homePage.validateSearchElements();
        homePage.sendInputsToSearchBar("Cairo" , "Cairo Governorate, Egypt");
        homePage.selectCheckInAndCheckOutDate("2024-06-22", "2024-08-13");
        homePage.clickOnSearchButton();
    }
}
