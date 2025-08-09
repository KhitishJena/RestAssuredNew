package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/satish/Features",
				glue = {"com.satish.StepDefinations"},
				tags = "@AddPlace",
				dryRun = false,
				plugin = {"pretty", "html:target/cucumber-reports.html",
						  "json:target/jsonReports/cucumber.json",
						  "junit:target/cucumber.xml"})
public class TestRunner {

}

/*
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.ConfigurationParameters;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.IncludeEngines;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameters({
    @ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.satish.StepDefinations"),
    @ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "json:target/jsonReports/cucumber.json")
})
public class TestRunner {
}*/