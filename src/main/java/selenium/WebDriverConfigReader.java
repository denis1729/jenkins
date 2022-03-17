package selenium;

import org.apache.log4j.Logger;
import utils.JsonReader;

public class WebDriverConfigReader {
    private Logger log = Logger.getLogger(getClass());

    private static final String BROWSER = "browser";
    private static final String DRIVER = "web driver";
    private static final String IMPLICIT_WAIT_TIME = "implicit wait time";
    private static final String EXPLICIT_WAIT_TIME = "explicit wait time";
    private static final String WAIT_SLEEP_TIME = "wait sleep time";

    private String browser;
    private int implicitWaitTime;
    private int explicitWaitTime;
    private int waitSleepTime;

    private static WebDriverConfigReader instance;

    /**
     * Constructor of WebDriverConfigReader.
     * Gets WebDriverConfigReader as Singleton.
     *
     * @return a instance.
     */
    public static WebDriverConfigReader getInstance() {
        if (instance == null) {
            instance = new WebDriverConfigReader();
        }
        return instance;
    }

    /**
     * Initializes According the config file.
     *
     * @param webDriverConfigFilename The configuration parameters.
     */
    public void initialize(String webDriverConfigFilename) {
        log.info("WebDriverConfigReader initialize: Read the driver configuration settings");
        JsonReader configReader = new JsonReader(webDriverConfigFilename);

        browser = "chrome";  //Get the browser system property
        log.info("Browser name --> ".concat(browser));

        implicitWaitTime = Integer.valueOf(configReader.getKeyValue(DRIVER, IMPLICIT_WAIT_TIME));
        explicitWaitTime = Integer.valueOf(configReader.getKeyValue(DRIVER, EXPLICIT_WAIT_TIME));
        waitSleepTime = Integer.valueOf(configReader.getKeyValue(DRIVER, WAIT_SLEEP_TIME));
    }

    /**
     * Gets the browser in which the tests are being executed.
     *
     * @return Browser.
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * Gets the implicit wait time set for the WebDriver.
     *
     * @return The implicit time.
     */
    public int getImplicitWaitTime() {
        return implicitWaitTime;
    }

    /**
     * Gets the explicit wait time set for the WebDriver.
     *
     * @return The explicit time.
     */
    public int getExplicitWaitTime() {
        return explicitWaitTime;
    }

    /**
     * Gets the sleep time wait set for the WebDriver.
     *
     * @return Sleep time wait.
     */
    public int getWaitSleepTime() {
        return waitSleepTime;
    }
}
