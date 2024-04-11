package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import org.checkerframework.checker.mustcall.qual.CreatesMustCallFor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GlobalVariables;

public class CalendarColourPage {
    protected IOSDriver driver;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Calendar Colour']")
    private RemoteWebElement calendarColourNavigationBar;
    @iOSXCUITFindBy(accessibility = "Blue")
    private RemoteWebElement blueColourButton;
    @iOSXCUITFindBy(xpath = "(//XCUIElementTypeButton[@name='Add Calendar'])[2]")
    private RemoteWebElement addCalendarColourButton;


    public CalendarColourPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Calendar Colour page is loaded")
    public boolean calendarColourPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(calendarColourNavigationBar)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Click blue colour button")
    public void clickBlueColourButton() {
        blueColourButton.click();
    }

    @Step("Click add calendar colour button")
    public void clickAddCalendarColourButton() {
        addCalendarColourButton.click();
    }


}
