package lab2.integrated.systems.bookingapplicationspringboot.e2e.steps.hooks;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserConfiguration {
    @BeforeAll
    public static void beforeAll() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "firefox":
                Configuration.browser = "firefox";
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--width=1920", "--height=1080");
                Configuration.browserCapabilities = firefoxOptions;
                break;

            case "chrome":
                Configuration.browser = "chrome";
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                Configuration.browserCapabilities = chromeOptions;
                break;

            case "edge":
                Configuration.browser = "edge";
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                Configuration.browserCapabilities = edgeOptions;
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        Configuration.baseUrl = "http://localhost:8080";
        Configuration.timeout = 10000;
    }
}
