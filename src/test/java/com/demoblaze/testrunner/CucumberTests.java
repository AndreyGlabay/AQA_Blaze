package com.demoblaze.testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources", // Features path;
        glue = {"com.demoblaze.scenarios"}, // Step Definitions path;
        plugin = {"pretty", "html:target/cucumber-reports"}
)

public class CucumberTests {
}
