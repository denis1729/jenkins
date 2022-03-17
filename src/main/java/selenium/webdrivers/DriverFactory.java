package selenium.webdrivers;
import org.openqa.selenium.WebDriver;
import selenium.WebDriverConfigReader;

import java.util.HashMap;
import java.util.Map;

public final class DriverFactory {

    private static final String FIREFOX = "firefox";
    private static final String CHROME = "chrome";
    private static final String EDGE = "edge";

    /**
     * Constructor.
     */
    private DriverFactory() {
    }

    /**
     * Gets the correct instance of IWebDriver according the name given by parameter.
     * @return The instance of web driver.
     */
    public static WebDriver getDriver() {
        Map<String, IDriver> strategyBrowser = new HashMap<String, IDriver>();
        strategyBrowser.put(FIREFOX, new FireFox());
        strategyBrowser.put(CHROME, new Chrome());
        strategyBrowser.put(EDGE, new Edge());

        return strategyBrowser.get(WebDriverConfigReader.getInstance().getBrowser().toLowerCase()).initDriver();
    }
}
