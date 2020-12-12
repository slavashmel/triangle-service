package steps;

import Base.BaseUtil;
import Base.CpqElementService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.RoePage;
import pojo.User;

import java.util.List;
import java.util.Map;

import static steps.CpquiApiSteps.createQuoteBody;

public class CpquiUiSteps extends BaseUtil {

    @And("Login to ROE with")
    public void loginToROEWith(DataTable arg) {
        driver.navigate().to(baseURI + quoteUrl);
        driver.findElement(By.className("title")).isDisplayed();

        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        User user = new User(
                table.get(0).get("username"),
                table.get(0).get("password"));

        System.out.println("Try login with Username: \"" + user.getUsername() + "\" and Password: \"" + user.getPassword() + "\"");
        LoginPage page = new LoginPage(driver);
        page.EnterLoginCredentials(user.getUsername(), user.getPassword());
        page.ClickLoginButton();

        CpqElementService.waitPageLoading();

        CpqElementService.waitVisibilityOfElement(RoePage.favoritesLocator);
        Assert.assertEquals("Page is not loaded.", true, driver.findElement(RoePage.summaryAreaTotals).isDisplayed());
        System.out.println("Quote page is loaded");
    }

    @And("Add Subscription from Favorites with name {string}")
    public void addSubscriptionFromFavoritesWithName(String subscriptionName) {
        CpqElementService.waitVisibilityOfElement(RoePage.favoritesLocator);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        CpqElementService.clickOnElement(RoePage.subscriptionLocator(subscriptionName));

        CpqElementService.waitPageLoading();
        try {
            CpqElementService.waitPageLoading();
        } catch (Exception ignored) {

        }
        System.out.println("Subscription \"" + subscriptionName + "\" is added");
    }

    @And("Raise Quantity")
    public void raiseQuantity() {
        CpqElementService.clickOnElement(RoePage.quantityUp);

        CpqElementService.applySelection();

        System.out.println("Quantity increased");
    }

    @And("Add Remark {string}")
    public void addRemark(String remark) {
        driver.findElement(RoePage.remarkLocator).sendKeys(remark);

        CpqElementService.applySelection();

        System.out.println("Remark with text \"" + remark + "\" is added");
    }

    @And("Select Work Addon {string}")
    public void selectWorkAddon(String workAddonName) {
        CpqElementService.clickOnElement(RoePage.addonLocator(workAddonName));

        CpqElementService.applySelection();
        System.out.println("Work Addon \"" + workAddonName + "\" is selected");
    }

    @And("Change Pricing Date")
    public void changePricingDate() {
        CpqElementService.clickOnElement(RoePage.pricingDateCalendarIcon);

        CpqElementService.waitVisibilityOfElement(RoePage.pricingDatePrevMonth);
        CpqElementService.clickOnElement(RoePage.pricingDatePrevMonth);

        CpqElementService.waitVisibilityOfElement(RoePage.pricingDateFirstDayOfSecondWeek);
        CpqElementService.clickOnElement(RoePage.pricingDateFirstDayOfSecondWeek);

        CpqElementService.waitPageLoading();

        System.out.println("Pricing Date is changed on 1 week back");
    }

    @And("Change Market")
    public void changeMarket() {
        CpqElementService.clickOnElement(RoePage.marketSelector);
        CpqElementService.clickOnElement(RoePage.marketSecondValue);

        CpqElementService.waitPageLoading();

        System.out.println("Market is changed to 1st available value");
    }

    @And("Duplicate the package")
    public void duplicateThePackage() {
        CpqElementService.clickOnElement(RoePage.duplicatePackage);

        CpqElementService.waitPageLoading();

        System.out.println("Package is cloned");
    }

    @And("Validate Scenario name")
    public void validateScenarioName() {
        String scenarioActual = driver.findElement(RoePage.scenarioLocator).getText();
        Assert.assertEquals(createQuoteBody.getScenario(), scenarioActual);

        System.out.println("Scenario name is correct");
    }

    @And("Add device {string}")
    public void addDevice(String deviceName) {
        CpqElementService.clickOnElement(RoePage.devicesExpander);

        driver.findElement(RoePage.devicesSearchField).sendKeys(deviceName);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(RoePage.deviceToSelect(deviceName)));
        CpqElementService.waitVisibilityOfElement(RoePage.deviceToSelect(deviceName));

        CpqElementService.clickOnElement(RoePage.deviceToSelect(deviceName));

        CpqElementService.waitPageLoading();

        System.out.println("Device \""  + deviceName + "\" is added");
    }

    @And("Select color {string}")
    public void selectColor(String deviceColor) {
        CpqElementService.clickOnElement(RoePage.deviceColorSelector(deviceColor));

        CpqElementService.waitPageLoading();

        System.out.println("Color \""  + deviceColor + "\" is selected");
    }

    @And("Select SIM card with name {string}")
    public void selectSIMCardWithName(String simCardName) {
        CpqElementService.clickOnElement(RoePage.simCardToSelect(simCardName));

        CpqElementService.waitPageLoading();
    }
}
