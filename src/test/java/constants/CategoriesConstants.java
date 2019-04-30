package constants;

import org.openqa.selenium.By;

public enum CategoriesConstants implements Constant {

    CATEGORY_NAME(By.id(baseId + "category_name"), null);

    By androidElement;
    By iosElement;

    CategoriesConstants(By androidElement, By iosElement) {
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
