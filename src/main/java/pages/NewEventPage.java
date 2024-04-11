package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class NewEventPage {
    protected IOSDriver driver;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='New Event']")
    private RemoteWebElement newEventPageHeader;

    @iOSXCUITFindBy(accessibility = "Title")
    private RemoteWebElement titleField;
    @iOSXCUITFindBy(accessibility = "Starts")
    private RemoteWebElement eventStartButton;
    @iOSXCUITFindBy(accessibility = "Show year picker")
    private RemoteWebElement showYearAndDatePickerButton;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[1]")
    private RemoteWebElement monthPicker;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[2]")
    private RemoteWebElement yearPicker;
    @iOSXCUITFindBy(accessibility = "Hide year picker")
    private RemoteWebElement hideYearAndDatePickerButton;
    @iOSXCUITFindBy(className = "XCUIElementTypeCollectionView")
    private RemoteWebElement calendarDateCollectionView;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell[contains(@name,'Starts')]/*[@name='Date and Time Picker']/XCUIElementTypeButton)[2]")
    private RemoteWebElement startsHourPickerButton;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[1]")
    private RemoteWebElement hourPicker;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[2]")
    private RemoteWebElement minutePicker;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypePickerWheel)[3]")
    private RemoteWebElement timePeriodPicker;
    @iOSXCUITFindBy(accessibility = "Ends")
    private RemoteWebElement eventEndButton;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeCell[contains(@name,'Ends')]/*[@name='Date and Time Picker']/XCUIElementTypeButton)[2]")
    private RemoteWebElement endsHourPickerButton;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Travel Time']")
    private RemoteWebElement travelTimeButton;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='30 minutes']")
    private RemoteWebElement thirtyMinuteButton;
    @iOSXCUITFindBy(className = "XCUIElementTypeSwitch")
    private RemoteWebElement allDaySwitch;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='Add'])[2]")
    private RemoteWebElement eventAddButton;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='DayViewContainerView']/XCUIElementTypeButton[1]")
    private RemoteWebElement backButton;


    public NewEventPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("New calendar event page is loaded")
    public boolean newEventPageLoaded() {
        return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(newEventPageHeader)).isDisplayed();
    }

    @Step("Add event name: {0}")
    public void addEventName(String eventName) {
        titleField.clear();
        titleField.sendKeys(eventName);
    }

    @Step("Select start date {0} {1} {2}")
    public void selectStartDate(String date, String month, String year) {
        eventStartButton.click();
        showYearAndDatePickerButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(monthPicker)).sendKeys(month);
        yearPicker.sendKeys(year);
        hideYearAndDatePickerButton.click();
        calendarDateCollectionView.isDisplayed();
        driver.findElement(AppiumBy.accessibilityId(date)).click();
    }

    @Step("Select start time {0}:{1} {2}")
    public void selectStartTime(String hour, String minutes, String timePeriod) {
        startsHourPickerButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(hourPicker)).sendKeys(hour);
        minutePicker.sendKeys(minutes);
        timePeriodPicker.sendKeys(timePeriod);
    }

    @Step("Select end date {0} {1} {2}")
    public void selectEndDate(String date, String month, String year) {
        eventEndButton.click();
        showYearAndDatePickerButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(monthPicker)).sendKeys(month);
        yearPicker.sendKeys(year);
        hideYearAndDatePickerButton.click();
        calendarDateCollectionView.isDisplayed();
        driver.findElement(AppiumBy.accessibilityId(date)).click();
    }

    @Step("Select end time {0}:{1} {2}")
    public void selectEndTime(String hour, String minutes, String timePeriod) {
        endsHourPickerButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(hourPicker)).sendKeys(hour);
        minutePicker.sendKeys(minutes);
        timePeriodPicker.sendKeys(timePeriod);
    }

    @Step("Add travel time")
    public void addTravelTime() {
        // As travel time has defined set of values, time value is not set as parameter.
        travelTimeButton.click();
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(thirtyMinuteButton)).click();
    }

    @Step("Click all day switch")
    public void clickAllDaySwitch() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(allDaySwitch)).click();
    }

    @Step("Verify that start time is not visible")
    public boolean verifyStartTimeIsNotVisible() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(startsHourPickerButton)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Verify that end time is not visible")
    public boolean verifyEndTimeIsNotVisible() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(endsHourPickerButton)).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    @Step("Click event add button")
    public void clickEventAddButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(eventAddButton)).click();
    }

    @Step("Click back button")
    public void clickBackButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(backButton)).click();
    }




}
