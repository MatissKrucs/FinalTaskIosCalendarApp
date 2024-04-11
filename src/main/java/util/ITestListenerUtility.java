package util;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerUtility extends ScreenShotUtility implements ITestListener {
    public void onTestSuccess(ITestResult tr) {
        captureScreenShot(tr, "pass");

    }

    public void onTestFailure(ITestResult tr) {
        captureScreenShot(tr, "fail");

    }

}
