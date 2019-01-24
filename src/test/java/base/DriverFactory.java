package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    public static String APP_PACKAGE = "com.getir.getirtestingcasestudy";
    protected static AppiumDriver<MobileElement> driver = null;
    private static boolean androidTest = true;
    private static Logger LOGGER = Logger.getLogger(DriverFactory.class);

    public static void createDriver() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (androidTest) {
            String APP_ACTIVITY = APP_PACKAGE + ".ui.login.LoginActivity";
//            File apk = new File(System.getProperty("user.dir") + "/app/app.apk");

            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Samsung S8");
            capabilities.setCapability("appPackage", APP_PACKAGE);
            capabilities.setCapability("automationName", "uiautomator2");
            capabilities.setCapability("appActivity", APP_ACTIVITY);
            capabilities.setCapability("udid", "ce04171418dee0010c");
            capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
//            capabilities.setCapability("app", apk.getAbsolutePath()); //adb install -t parametresi ile kurmak gerekiyor
            capabilities.setCapability("unicodeKeyboard", false);
            capabilities.setCapability("resetKeyboard", true);
            capabilities.setCapability("noReset", true);
            capabilities.setCapability("newCommandTimeout", "6000");
            capabilities.setCapability("interKeyDelay", 100);

            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        } else {
            LOGGER.info("IOS driver created...");
        }

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        ThreadLocalDriver.setDriver(driver);

    }
}