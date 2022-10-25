package selenium;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import selenium.webdrivers.DriverFactory;
import utils.LoggerSingleton;

import java.time.Duration;

public class WebDriverManager {
    private final Logger log = LoggerSingleton.getInstance().getLogger(getClass().getName());
    private WebDriver webDriver;
    private final Wait<WebDriver> webDriverWait;

    /**
     * Initializes the settings for the driver.
     */
    public WebDriverManager() {
        log.info("WebDriverManager initialize: Initializing the web driver");
        this.webDriver = DriverFactory.getDriver();
        this.webDriver.manage().window().maximize();
        WebDriverConfigReader webDriverConfigReader = WebDriverConfigReader.getInstance();
        this.webDriver.manage().timeouts().
                implicitlyWait(Duration.ofSeconds(webDriverConfigReader.getImplicitWaitTime()));
        webDriverWait = new FluentWait<>(webDriver).
                withTimeout(Duration.ofSeconds(webDriverConfigReader.getExplicitWaitTime())).
                pollingEvery(Duration.ofMillis(webDriverConfigReader.getWaitSleepTime())).
                ignoring(NoSuchElementException.class);
    }

    /**
     * Gets the WebDriver.
     * @return WebDriver.
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * Gets the WebDriver Wait.
     * @return WebDriverWait.
     */
    public Wait<WebDriver> getWait() {
        return webDriverWait;
    }

    /**
     * Closes all the browser instances.
     */
    public void quitDriver() {
        try {
            log.info("Quit driver");
            webDriver.quit();
        } catch (WebDriverException e) {
            log.error("Unable to quit the WebDriver", e);
        }
        webDriver = null;
    }
}
