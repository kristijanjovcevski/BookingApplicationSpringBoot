package lab2.integrated.systems.bookingapplicationspringboot.e2e.runners;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "lab2.integrated.systems." +
        "bookingapplicationspringboot.e2e," + "lab2.integrated.systems" +
        ".bookingapplicationspringboot.e2e.steps.hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty," +
        " html:target/cucumber-report.html, json:target/cucumber.json")
/*@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME,
        value = "@smoke and @regression")*/
public class CucumberE2EIT {
}
