package steps;

import Base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hooks extends BaseUtil {

//    @Before(order = 10)
//    public void initializeWebDriver() {
//        System.out.println("Opening the browser : Chrome");
//
//        if(OSDetector().equals("Mac"))
//            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_87.0.4280.88_mac");
//        else if(OSDetector().equals("Windows"))
//            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_87.0.4280.88_win.exe");
//
//        driver = new ChromeDriver();
//        driver.manage().window().setSize(new Dimension(1500, 1000)); //размер окна
//        wait = new WebDriverWait(driver, 15); //инициализируем переменную wait
//    }

//    @Before(order = 20)
//    public void generateRandomValues() {
//        randomRelationId = "relationId" + generateRandomLong(10);
//        randomCustomerId = "id" + generateRandomLong(16);
//        randomQuoteName = "AT-" + generateRandomLong(12) + "-Quote";
////        correlationId = "AT" + generateRandomLong(10);
//    }

//    @After(order = 20)
//    public void tearDownTest(Scenario scenario) {
//        if (scenario.isFailed()) {
//            //Take screenshot logic
//            System.out.println(scenario.getName() + " IS FAILED!");
//        }
//        System.out.println("Closing the browser : Chrome");
//        driver.quit();
//    }

    @After(order = 10)
    public void getScenarioInfo(Scenario scenario) {
        System.out.println(scenario.getId());
        System.out.println(scenario.getName() + " is " + scenario.getStatus() + ". Tags" + scenario.getSourceTagNames());
    }
}
