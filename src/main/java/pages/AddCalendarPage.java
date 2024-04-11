package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import io.qameta.allure.Step;
import org.checkerframework.checker.mustcall.qual.CreatesMustCallFor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;

public class AddCalendarPage {
    protected IOSDriver driver;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Add Calendar']")
    private RemoteWebElement AddCalendarNavigationBar;
    @iOSXCUITFindBy(className = "XCUIElementTypeTextField")
    private RemoteWebElement calendarNameTextField;
    @iOSXCUITFindBy(accessibility = "chevron")
    private RemoteWebElement colourDropDownButton;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='Done'])[2]")
    private RemoteWebElement doneButton;

    public AddCalendarPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Add calendar page is loaded")
    public boolean addCalendarPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(AddCalendarNavigationBar)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Add calendar name: {0}")
    public void addCalendarName(String calendarName) {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(calendarNameTextField)).sendKeys(calendarName);
    }

    @Step("Click colour drop down button")
    public void clickColourDropDownButton() {
        colourDropDownButton.click();
    }

    @Step("Click done button")
    public void clickDoneButton() {
        new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                (ExpectedConditions.visibilityOf(doneButton)).click();
    }
}
