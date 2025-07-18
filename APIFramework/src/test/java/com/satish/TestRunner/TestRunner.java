package com.satish.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@SuppressWarnings("deprecation")
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/com/satish/Features",
				glue = {"com.satish.StepDefinations"})
public class TestRunner {

}
