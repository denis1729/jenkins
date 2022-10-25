package config;

import org.slf4j.Logger;
import selenium.WebDriverConfigReader;
import utils.LoggerSingleton;
import utils.PathReader;

/**
 * The AutomationConfig class reads configuration files to run test automation.
 *
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public class AutomationConfig {

    private static AutomationConfig instance;

    /**
     * Constructor method to initialize the necessary configuration.
     */
    private AutomationConfig() {
        Logger log = LoggerSingleton.getInstance().getLogger(getClass().getName());
        log.info("Start configuration files");
        String environmentConfigFileName = PathReader.getPathConfig() + "EnvironmentConfig.json";
        EnvironmentConfigReader.getInstance().initialize(environmentConfigFileName);
        String remoteServerConfigFileName = PathReader.getPathConfig() + "RemoteServerConfig.json";
        ServerRemoteConfigReader.getInstance().initialize(remoteServerConfigFileName);
        String webDriverConfigFilename = PathReader.getPathConfig() + "WebDriverConfig.json";
        WebDriverConfigReader.getInstance().initialize(webDriverConfigFilename);
        String usersConfigFileName = PathReader.getPathConfig() + "User.json";
        UsersConfigReader.getInstance().initialize(usersConfigFileName);
        String serversConfigFileName = PathReader.getPathConfig() + "Servers.json";
        ServersConfigReader.getInstance().initialize(serversConfigFileName);
    }

    /**
     * Gets a new instance if it is null.
     *
     * @return the instance of SelectSiteAutomation.
     */
    public static AutomationConfig getInstance() {
        if (instance == null) {
            instance = new AutomationConfig();
        }
        return instance;
    }
}
