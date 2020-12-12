package Base;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.RoePage;

/**
 * CPQ UI service methods
 */

public class CpqElementService {

    public static void waitPageLoading() {
        BaseUtil.wait.until(ExpectedConditions.visibilityOfElementLocated(RoePage.ncLoadingImage));
        BaseUtil.wait.until(ExpectedConditions.invisibilityOfElementLocated(RoePage.ncLoadingImage));
    }

    public static void waitVisibilityOfElement(By arg) {
        BaseUtil.wait.until(ExpectedConditions.visibilityOfElementLocated(arg));
    }

    public static void applySelection() {
        BaseUtil.wait.until(ExpectedConditions.visibilityOfElementLocated(RoePage.packageTitleLocator)).click();
        waitPageLoading();
    }

    public static void clickOnElement(By arg) {
        BaseUtil.driver.findElement(arg).click();
    }
}
