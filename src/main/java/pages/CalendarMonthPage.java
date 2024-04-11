package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;
import util.Helpers;

public class CalendarMonthPage extends Helpers {

    protected IOSDriver driver;

    @iOSXCUITFindBy(accessibility = "MonthViewContainerView")
    private RemoteWebElement calendarMonthViewContainer;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Today']")
    private RemoteWebElement todayButton;


    public CalendarMonthPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Calendar month page is loaded")
    public boolean calendarMonthPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(calendarMonthViewContainer)).isDisplayed();
    }

    @Step("Verify that event is added in this date: {0}")
    public boolean checkEventByDate(String date) {
           String eventStartDay = String.format("//XCUIElementTypeButton[@name='%s']", date);
           String events = driver.findElement(AppiumBy.xpath(eventStartDay)).getAttribute("value");
           return !events.equalsIgnoreCase("No events");
    }

    @Step("Click calendar button to return to calendar home page")
    public void returnToHome() {
        todayButton.click();
        todayButton.click();
    }

}
