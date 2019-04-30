package steps;

import base.ThreadLocalDriver;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ConstantHelper;

public class Steps {

    protected AppiumDriver driver = ThreadLocalDriver.getDriver();
    private Logger LOGGER = Logger.getLogger(Steps.class);

    @Step("Sendkey <text> to <enumName>")
    public void sendKeyToEnum(String text, String enumName) {
        MobileElement el = waitForElement(enumName, 15, true);
        el.click();
        el.clear();
        el.sendKeys(text);

        LOGGER.info("SendKey : " + enumName + " <- '" + text + "'");
    }

    @Step("Click to <enumName>")
    public void clickToEnum(String enumName) {
        waitForElement(enumName, 15, true).click();
        LOGGER.info("Clicked : " + enumName);
    }

    @Step("Is exist <enumName> in <seconds> seconds ?")
    public void isExist(String enumName, String seconds) {
        try {
            waitForElement(enumName, 10, false);
            LOGGER.info("isExist? : " + enumName + " <- true");
        } catch (Exception e) {
            Assert.fail(enumName + " not found");
        }
    }

    public MobileElement waitForElement(String enumName, int seconds, boolean assertion) {
        MobileElement element = null;
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        By elementBy = ConstantHelper.getEnumWithName(enumName).getElementBy();

        System.out.println("* " + enumName + " elementBy : " + elementBy);

        if (assertion) {
            try {
                element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
            } catch (Exception e) {
                Assert.fail(enumName + " not found!");
            }
        } else {
            element = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
        }

        return element;
    }
}
