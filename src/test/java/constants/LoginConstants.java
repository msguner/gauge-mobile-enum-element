package constants;

import org.openqa.selenium.By;

public enum LoginConstants implements Constant {

    USERNAME(By.id(baseId + "email"), null),
    PASSWORD(By.id(baseId + "password"), null),
    LOGIN_BUTTON(By.id(baseId + "email_sign_in_button"), null);

    By androidElement;
    By iosElement;

    LoginConstants(By androidElement, By iosElement) {
        this.androidElement = androidElement;
        this.iosElement = iosElement;
    }

    @Override
    public By getAndroidBy() {
        return androidElement;
    }

    @Override
    public By getIOSBy() {
        return iosElement;
    }
}
