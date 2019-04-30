package constants;

import base.DriverFactory;
import base.ThreadLocalDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;


public interface Constant {

    String baseId = DriverFactory.APP_PACKAGE + ":id/"; //for android constants

    By getAndroidBy();

    By getIOSBy();

    default By getElementBy() {
        return (ThreadLocalDriver.getDriver() instanceof AndroidDriver ? getAndroidBy() : getIOSBy());
    }
}
