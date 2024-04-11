package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

@Epic("Regression tests")
@Feature("Calendar tests")
public class AddNewCalendarTest extends DriverSetup {

    @Severity(SeverityLevel.NORMAL)
    @Description("Test description: Create new calendar and validate functionality")
    @Test(testName = "Add New Calendar test")
    public void calendarTest() throws InterruptedException {

        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

        calendarHomePage.clickCalendarsButton();
        Assert.assertTrue(calendarsPage.calendarsPageLoaded(), "Calendars page is not loaded");

        calendarsPage.clickAddCalendarButton();
        Assert.assertTrue(addCalendarPage.addCalendarPageLoaded(), "Add calendar page is not loaded");

        addCalendarPage.addCalendarName("Holiday calendar");

        addCalendarPage.clickColourDropDownButton();
        Assert.assertTrue(calendarColourPage.calendarColourPageLoaded(), "Calendar Colour page is not loaded");

        calendarColourPage.clickBlueColourButton();
        calendarColourPage.clickAddCalendarColourButton();
        Assert.assertTrue(addCalendarPage.addCalendarPageLoaded(), "Add calendar page is not loaded");
        addCalendarPage.clickDoneButton();

        Assert.assertTrue(calendarsPage.calendarsPageLoaded(), "Calendars page is not loaded");

        Assert.assertTrue(calendarsPage.validateCalendarIsDisplayed("Holiday calendar"));

        calendarsPage.clickHideAllButton();

        Assert.assertFalse(calendarsPage.validateCalendarsSelected("Calendar"));
        Assert.assertFalse(calendarsPage.validateCalendarsSelected("Holiday calendar"));

        calendarsPage.clickShowAllButton();

        calendarsPage.clickCalendarInfoButton("Holiday calendar");
        Assert.assertTrue(editCalendarPage.editCalendarPageLoaded(), "Edit calendar page is not loaded");

        editCalendarPage.clickDeleteCalendarButton();
        Assert.assertTrue(calendarsPage.calendarsPageLoaded(), "Calendars page is not loaded");


        Assert.assertTrue(calendarsPage.validateCalendarIsDisplayed("Calendar"), "Calendar is not displayed");
        Assert.assertFalse(calendarsPage.validateCalendarIsDisplayed("Holiday calendar"), "Holiday calendar is not deleted");

        calendarsPage.clickDoneButton();
        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

        calendarMonthPage.returnToHome();
        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");
    }
}
