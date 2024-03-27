package com.demoblaze.testrunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources"},
        glue = "com.demoblaze.scenarios",
        plugin = {"testng:testng.xml"}
)

public class CucumberTestRunner {
}
