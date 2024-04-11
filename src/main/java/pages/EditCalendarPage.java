package pages;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import util.GlobalVariables;

public class EditCalendarPage {
    protected IOSDriver driver;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Edit Calendar']")
    private RemoteWebElement editCalendarNavigationBar;
    @iOSXCUITFindBy(accessibility = "Delete Calendar")
    private RemoteWebElement deleteCalendarButton;
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeButton[@name='Delete Calendar']")
    private RemoteWebElement confirmDeleteCalendarButton;

    public EditCalendarPage(IOSDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Step("Edit calendar page is loaded")
    public boolean editCalendarPageLoaded() {
        try {
            return new WebDriverWait(driver, GlobalVariables.globalTimeout).until
                    (ExpectedConditions.visibilityOf(editCalendarNavigationBar)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Step("Delete created calendar")
    public void clickDeleteCalendarButton() {
        deleteCalendarButton.click();
        confirmDeleteCalendarButton.click();
    }
}


