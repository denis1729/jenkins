package config;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import selenium.WebDriverConfigReader;
import selenium.WebDriverManager;

import java.io.File;

public class AutomationSFDC {
    private Logger log = Logger.getLogger(getClass());

    private final String webDriverConfigFilename =
            System.getProperty("user.dir") + File.separator + "config" + File.separator + "WebDriverConfig.json";
    private final String usersConfigFileName =
            System.getProperty("user.dir") + File.separator + "config" + File.separator + "User.json";
    private final String serversConfigFileName =
            System.getProperty("user.dir") + File.separator + "config" + File.separator + "Servers.json";

    private static AutomationSFDC instance;

    /**
     * Constructor method to initialize the necessary configuration.
     */
    private AutomationSFDC() {
        PropertyConfigurator.configure("log.properties");
        WebDriverConfigReader.getInstance().initialize(webDriverConfigFilename);
        UsersConfigReader.getInstance().initialize(usersConfigFileName);
        ServersConfigReader.getInstance().initialize(serversConfigFileName);
    }

    /**
     * Gets a new instance if it is null.
     *
     * @return the instance of SelectSiteAutomation.
     */
    public static AutomationSFDC getInstance() {
        if (instance == null) {
            instance = new AutomationSFDC();
        }
        return instance;
    }

    /**
     * Starts the execution.
     */
    public void startUp() {
        log.info("-----Start Automation execution-----");
        WebDriverManager.getInstance().initialize();
    }

    /**
     * Ends the execution.
     */
    public void shutDown() {
        WebDriverManager.getInstance().quitDriver();
    }
}
