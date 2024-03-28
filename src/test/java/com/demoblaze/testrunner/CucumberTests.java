package com.demoblaze.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.testng.annotations.Test;

@RunWith(Cucumber.class)
@Test
@CucumberOptions(
        features = "src/test/resources", // Features path;
        glue = {"com.demoblaze.scenarios"}, // Step Definitions path;
        plugin = {"pretty", "html:target/cucumber-reports", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}
)

public class CucumberTests extends AbstractTestNGCucumberTests {
}
