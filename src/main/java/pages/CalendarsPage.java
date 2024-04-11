package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class CalendarsPage {
    protected IOSDriver driver;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Calendars']")
    private RemoteWebElement calendarsNavigationBar;
    @iOSXCUITFindBy(accessibility = "Add Calendar")
    private RemoteWebElement addCalendarButton;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='Add Calendar'])[2]")
    private RemoteWebElement selectAddCalendarButton;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Add Calendar']")
    private RemoteWebElement AddCalendarNavigationBar;
    @iOSXCUITFindBy(accessibility = "Hide All")
    private RemoteWebElement hideAllButton;
    @iOSXCUITFindBy(accessibility = "Show All")
    private RemoteWebElement showAllButton;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    private RemoteWebElement doneButton;

    public CalendarsPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Calendars page is loaded")
    public boolean calendarsPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(calendarsNavigationBar)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click add calendar button")
    public void clickAddCalendarButton() {
        addCalendarButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(selectAddCalendarButton)).click();
    }

    @Step("Validate that calendar: {0} is displayed")
    public boolean validateCalendarIsDisplayed(String calendarName) {
        try {
            WebElement nameElement = driver.findElement(AppiumBy.accessibilityId(calendarName));
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(nameElement)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click hide all button")
    public void clickHideAllButton() {
        hideAllButton.click();
    }

    @Step("Validate calendar: {0} is selected")
    public boolean validateCalendarsSelected(String calendarName) {
        String eventStartDay = String.format("//XCUIElementTypeStaticText[@name='%s']/preceding-sibling::" +
                "XCUIElementTypeImage", calendarName);
        String events = driver.findElement(AppiumBy.xpath(eventStartDay)).getAttribute("label");
        return events.equalsIgnoreCase("selected");
    }

    @Step("Click show all button")
    public void clickShowAllButton() {
        showAllButton.click();
    }

    @Step("Click calendar info button")
    public void clickCalendarInfoButton(String calendarName) {
        String calendarInfoButton = String.format("//XCUIElementTypeStaticText[@name='%s']/following-sibling::" +
                "XCUIElementTypeButton", calendarName);
        driver.findElement(AppiumBy.xpath(calendarInfoButton)).click();
    }

    public void clickDoneButton() {
        doneButton.click();
    }



}


