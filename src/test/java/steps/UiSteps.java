package steps;

import Base.BaseUtil;
import Base.ElementsService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.RoePage;
import pojo.User;

import java.util.List;
import java.util.Map;

public class UiSteps extends BaseUtil {

    @And("Login to ROE with")
    public void loginToROEWith(DataTable arg) {
        driver.navigate().to(baseURI + firstSide);
        driver.findElement(By.className("title")).isDisplayed();

        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        User user = new User(
                table.get(0).get("username"),
                table.get(0).get("password"));

        System.out.println("Try login with Username: \"" + user.getUsername() + "\" and Password: \"" + user.getPassword() + "\"");
        LoginPage page = new LoginPage(driver);
        page.EnterLoginCredentials(user.getUsername(), user.getPassword());
        page.ClickLoginButton();

        ElementsService.waitPageLoading();

        ElementsService.waitVisibilityOfElement(RoePage.favoritesLocator);
        Assert.assertEquals("Page is not loaded.", true, driver.findElement(RoePage.summaryAreaTotals).isDisplayed());
        System.out.println("Quote page is loaded");
    }

    @And("Add Subscription from Favorites with name {string}")
    public void addSubscriptionFromFavoritesWithName(String subscriptionName) {
        ElementsService.waitVisibilityOfElement(RoePage.favoritesLocator);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ElementsService.clickOnElement(RoePage.subscriptionLocator(subscriptionName));

        ElementsService.waitPageLoading();
        try {
            ElementsService.waitPageLoading();
        } catch (Exception ignored) {

        }
        System.out.println("Subscription \"" + subscriptionName + "\" is added");
    }

    @And("Raise Quantity")
    public void raiseQuantity() {
        ElementsService.clickOnElement(RoePage.quantityUp);

        ElementsService.applySelection();

        System.out.println("Quantity increased");
    }

    @And("Add Remark {string}")
    public void addRemark(String remark) {
        driver.findElement(RoePage.remarkLocator).sendKeys(remark);

        ElementsService.applySelection();

        System.out.println("Remark with text \"" + remark + "\" is added");
    }

    @And("Select Work Addon {string}")
    public void selectWorkAddon(String workAddonName) {
        ElementsService.clickOnElement(RoePage.addonLocator(workAddonName));

        ElementsService.applySelection();
        System.out.println("Work Addon \"" + workAddonName + "\" is selected");
    }

    @And("Change Pricing Date")
    public void changePricingDate() {
        ElementsService.clickOnElement(RoePage.pricingDateCalendarIcon);

        ElementsService.waitVisibilityOfElement(RoePage.pricingDatePrevMonth);
        ElementsService.clickOnElement(RoePage.pricingDatePrevMonth);

        ElementsService.waitVisibilityOfElement(RoePage.pricingDateFirstDayOfSecondWeek);
        ElementsService.clickOnElement(RoePage.pricingDateFirstDayOfSecondWeek);

        ElementsService.waitPageLoading();

        System.out.println("Pricing Date is changed on 1 week back");
    }

    @And("Change Market")
    public void changeMarket() {
        ElementsService.clickOnElement(RoePage.marketSelector);
        ElementsService.clickOnElement(RoePage.marketSecondValue);

        ElementsService.waitPageLoading();

        System.out.println("Market is changed to 1st available value");
    }

    @And("Duplicate the package")
    public void duplicateThePackage() {
        ElementsService.clickOnElement(RoePage.duplicatePackage);

        ElementsService.waitPageLoading();

        System.out.println("Package is cloned");
    }

    @And("Add device {string}")
    public void addDevice(String deviceName) {
        ElementsService.clickOnElement(RoePage.devicesExpander);

        driver.findElement(RoePage.devicesSearchField).sendKeys(deviceName);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(RoePage.deviceToSelect(deviceName)));
        ElementsService.waitVisibilityOfElement(RoePage.deviceToSelect(deviceName));

        ElementsService.clickOnElement(RoePage.deviceToSelect(deviceName));

        ElementsService.waitPageLoading();

        System.out.println("Device \""  + deviceName + "\" is added");
    }

    @And("Select color {string}")
    public void selectColor(String deviceColor) {
        ElementsService.clickOnElement(RoePage.deviceColorSelector(deviceColor));

        ElementsService.waitPageLoading();

        System.out.println("Color \""  + deviceColor + "\" is selected");
    }

    @And("Select SIM card with name {string}")
    public void selectSIMCardWithName(String simCardName) {
        ElementsService.clickOnElement(RoePage.simCardToSelect(simCardName));

        ElementsService.waitPageLoading();
    }
}
