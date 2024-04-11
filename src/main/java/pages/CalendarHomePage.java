package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;
import util.Helpers;

public class CalendarHomePage extends Helpers {

    protected IOSDriver driver;

    @iOSXCUITFindBy(accessibility = "Calendar")
    private RemoteWebElement calendarHomePageContainer;
    @iOSXCUITFindBy(accessibility = "Add")
    private RemoteWebElement addButton;
    @iOSXCUITFindBy(accessibility = "Calendars")
    private RemoteWebElement calendarsButton;

    public CalendarHomePage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Calendar home page is loaded")
    public boolean calendarHomePageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(calendarHomePageContainer)).isDisplayed();
    }
    @Step("Click + to create New Event")
    public void clickAddButton() {
        addButton.click();
    }
    @Step("Click calendar button")
    public void clickCalendarsButton() {
        calendarsButton.click();
    }

}
