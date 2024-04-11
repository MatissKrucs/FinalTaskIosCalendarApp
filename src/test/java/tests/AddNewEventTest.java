package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.DriverSetup;

@Epic("Regression tests")
@Feature("Calendar tests")
public class AddNewEventTest extends DriverSetup {

    @Severity(SeverityLevel.NORMAL)
    @Description("Test description: Create new calendar event and verify that it is added")
    @Test(testName = "Add new calendar event test")
    public void calendarTest() throws InterruptedException {


        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

        calendarHomePage.clickAddButton();
        Assert.assertTrue(newEventPage.newEventPageLoaded(), "New calendar event page is not loaded");

        newEventPage.addEventName("Adventure Event");

        newEventPage.selectStartDate("24", "April", "2024");
        newEventPage.selectStartTime("11", "10", "AM");

        newEventPage.selectEndDate("25", "April", "2024");
        newEventPage.selectEndTime("12", "15", "PM");

        newEventPage.addTravelTime();

        newEventPage.clickAllDaySwitch();

        Assert.assertFalse(newEventPage.verifyStartTimeIsNotVisible(), "Time selector is not visible");
        Assert.assertFalse(newEventPage.verifyEndTimeIsNotVisible(), "Time selector is not visible");

        newEventPage.clickAllDaySwitch();

        newEventPage.clickEventAddButton();
        newEventPage.clickBackButton();

        Assert.assertTrue(calendarMonthPage.calendarMonthPageLoaded(), "Calendar month page is not loaded");

        Assert.assertTrue(calendarMonthPage.checkEventByDate("Wednesday, 24 April"), "No event at this date");
        Assert.assertTrue(calendarMonthPage.checkEventByDate("Thursday, 25 April"), "No event at this date");

        calendarMonthPage.returnToHome();
        Assert.assertTrue(calendarHomePage.calendarHomePageLoaded(), "Calendar home page is not loaded");

    }
}
