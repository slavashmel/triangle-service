package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/features"}, plugin = {"json:target/cucumber.json", "html:target/site/cucumber-pretty"}, glue = "steps")
public class TestRunner extends AbstractTestNGCucumberTests {
}
