package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
//                features = {".//Features/Login.feature", ".//Features/Customers.feature"}, // Run Specific Feature file
                features = ".//Features/",
//                features = {".//Features/Customers.feature"},
                glue = "stepDefinitions",
                dryRun = false,
                monochrome = true,
                plugin = {"pretty", "html:test.html"},
                tags = "@tag1"
        )

public class TestRun {
}
