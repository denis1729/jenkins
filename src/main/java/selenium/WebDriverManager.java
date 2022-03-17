package selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.webdrivers.DriverFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Silvia Valencia on 2/2/2018.
 * Class to manage the Web Driver.
 */
public class WebDriverManager {
    private Logger log = Logger.getLogger(getClass());
    private WebDriverConfigReader webDriverConfigReader = WebDriverConfigReader.getInstance();
    private WebDriver webDriver;
    private WebDriverWait webDriverWait;

    private static WebDriverManager instance = null;

    /**
     * Gets the instance of the Web Driver.
     * @return WebDriverManager.
     */
    public static WebDriverManager getInstance() {
        if (instance == null || instance.webDriver == null) {
            instance = new WebDriverManager();
        }
        return instance;
    }

    /**
     * Initializes the settings for the driver.
     */
    public void initialize() {
        log.info("WebDriverManager initialize: Initializing the web driver");
        this.webDriver = DriverFactory.getDriver();
        this.webDriver.manage().window().maximize();
        this.webDriver.manage().timeouts().
                implicitlyWait(webDriverConfigReader.getImplicitWaitTime(), TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(webDriver, webDriverConfigReader.getExplicitWaitTime(),
                webDriverConfigReader.getWaitSleepTime());
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
    public WebDriverWait getWait() {
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
