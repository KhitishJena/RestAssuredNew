package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/satish/Features",
				glue = {"com.satish.StepDefinations"},
				tags = "@End2End",
				dryRun = false,
				plugin = {"pretty", "html:target/cucumber-reports.html",
						  "json:target/jsonReports/cucumber.json",
						  "junit:target/cucumber.xml"})
public class TestRunner {

}
