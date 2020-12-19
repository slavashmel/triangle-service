package steps;

import Base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks extends BaseUtil {

    @After(order = 10)
    public void getScenarioInfo(Scenario scenario) {
        System.out.println(scenario.getId());
        System.out.println(scenario.getName() + " is " + scenario.getStatus() + ". Tags" + scenario.getSourceTagNames());
    }
}
