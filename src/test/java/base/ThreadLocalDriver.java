package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ThreadLocalDriver {

    private static ThreadLocal<AppiumDriver<MobileElement>> tlDriver = new ThreadLocal<AppiumDriver<MobileElement>>();

    public synchronized static AppiumDriver getDriver() {
        return tlDriver.get();
    }

    public synchronized static void setDriver(AppiumDriver<MobileElement> driver) {
        tlDriver.set(driver);
    }
}