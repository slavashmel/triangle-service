package pages;

import Base.BaseUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * ROE page elements
 */

public class RoePage extends BaseUtil {

    public RoePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static By ncLoadingImage = By.cssSelector("div.nc-loading-image");
    public static By favoritesLocator = By.cssSelector("#FavouriteCategory > div > div > table > tbody > tr:nth-child(1)");
    public static By packageTitleLocator = By.cssSelector("h1[class=\"roe-table-column-title roe-table-cell\"]");
    public static By quantityUp = By.cssSelector(".ui-spinner-up");
    public static By remarkLocator = By.cssSelector("[class=\"string-characteristic\"]");
    public static By summaryAreaTotals = By.className("tlo-list-table");
    public static By pricingDateCalendarIcon = By.cssSelector(".ui-datepicker-trigger");
    public static By pricingDatePrevMonth = By.cssSelector(".ui-datepicker-prev");
    public static By pricingDateFirstDayOfSecondWeek = By.cssSelector("#ui-datepicker-div > table > tbody > tr:nth-child(2) > td:nth-child(1) > a");
    public static By marketSelector = By.cssSelector("div.cpq_summary_dropdown_characteristic > select");
    public static By marketSecondValue = By.cssSelector("div.cpq_summary_dropdown_characteristic > select > option:nth-child(2)");
    public static By duplicatePackage = By.cssSelector(".duplicate_button");
    public static By scenarioLocator = By.cssSelector(".cpq_summary_so_characteristics > div");
    public static By devicesExpander = By.xpath("//h1[contains(text(),'@Work Subscription Devices')]");
    public static By devicesSearchField = By.cssSelector("[placeholder=\"Search handset\"]");

    public static By deviceToSelect(String deviceName) {
        return By.xpath("//span[contains(text(),'" + deviceName + "')]");
    }
    public static By deviceColorSelector(String deviceColor) {
        return By.xpath("//span[contains(text(),'" + deviceColor + "')]");
    }
    public static By simCardToSelect(String simCardName) {
        return By.xpath("//span[contains(text(),'" + simCardName + "')]");
    }
    public static By subscriptionLocator(String subscriptionName) {
        return By.xpath("//span[contains(text(),'" + subscriptionName + "')]");
    }
    public static By addonLocator(String workAddonName) {
        return By.xpath("//div[contains(text(),'@Work add-ons')]/../..//span[contains(text(),'" + workAddonName + "')]/../../../td");
    }

}
