package base;

import com.testinium.deviceinformation.DeviceInfo;
import com.testinium.deviceinformation.DeviceInfoImpl;
import com.testinium.deviceinformation.device.DeviceType;
import com.testinium.deviceinformation.model.Device;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static String APP_PACKAGE = "com.getir.getirtestingcasestudy";
    static String APP_ACTIVITY = APP_PACKAGE + ".ui.login.LoginActivity";
    private static AppiumDriver<MobileElement> driver = null;
    private static Logger LOGGER = Logger.getLogger(DriverFactory.class);
    static final String url = "http://0.0.0.0:4723/wd/hub";


    public static void createDriver() throws Exception {
        DeviceInfo deviceInfo = new DeviceInfoImpl(DeviceType.ALL);
        LOGGER.info("devices : " + deviceInfo.getDevices());
        Device device = deviceInfo.getFirstDevice();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.getDeviceProductName());
//        capabilities.setCapability(MobileCapabilityType.VERSION, device.getProductVersion());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, device.getProductVersion());
        capabilities.setCapability(MobileCapabilityType.UDID, device.getUniqueDeviceID());
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);

        if (device.getDeviceProductName().equals("Android")) {
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            //capabilities.setCapability(AndroidMobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
            //capabilities.setCapability(MobileCapabilityType.AUTO_WEBVIEW, true);
            capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, APP_PACKAGE);
            capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, APP_ACTIVITY);
            capabilities.setCapability(AndroidMobileCapabilityType.UNICODE_KEYBOARD, false);
            capabilities.setCapability(AndroidMobileCapabilityType.RESET_KEYBOARD, true);
            capabilities.setCapability("setWebContentsDebuggingEnabled", true);
            capabilities.setCapability("autoAcceptAlerts", true);
            capabilities.setCapability("interKeyDelay", 100);

            driver = new AndroidDriver<MobileElement>(new URL(url), capabilities);

        } else {
//            capabilities.setCapability(IOSMobileCapabilityType.BROWSER_NAME, MobileBrowserType.SAFARI);
            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            capabilities.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "6Z4H2M53MV");
            capabilities.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "iPhone Developer");
            capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.getir.ios.dev.v2");
            capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "6000");

            driver = new IOSDriver<MobileElement>(new URL(url), capabilities);
        }

        driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
        ThreadLocalDriver.setDriver(driver);

    }
}