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
    private final String environmentConfigFileName =
            System.getProperty("user.dir") + File.separator + "config" + File.separator + "EnvironmentConfig.json";
    private final String remoteServerConfigFileName =
            System.getProperty("user.dir") + File.separator + "config" + File.separator + "RemoteServerConfig.json";

    private static AutomationSFDC instance;

    /**
     * Constructor method to initialize the necessary configuration.
     */
    private AutomationSFDC() {
        PropertyConfigurator.configure("log.properties");
        EnvironmentConfigReader.getInstance().initialize(environmentConfigFileName);
        ServerRemoteConfigReader.getInstance().initialize(remoteServerConfigFileName);
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
}
