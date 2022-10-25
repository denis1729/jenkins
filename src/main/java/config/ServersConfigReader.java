package config;

import org.slf4j.Logger;
import utils.JsonReader;
import utils.LoggerSingleton;

/**
 * The ServersConfigReader class reads configuration files to run test automation.
 *
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public final class ServersConfigReader {
    private final Logger log = LoggerSingleton.getInstance().getLogger(getClass().getName());

    //web
    private static final String SERVER = "server";
    private static final String URL = "url";

    private String serverAlias;
    private String baseUrl;

    private static ServersConfigReader instance;

    /**
     * Gets instance of ServersConfigReader.
     *
     * @return the current instance.
     */
    public static ServersConfigReader getInstance() {
        if (instance == null) {
            instance = new ServersConfigReader();
        }
        return instance;
    }

    /**
     * Reads values from json file.
     *
     * @param ServersConfigFileName servers configuration file.
     */
    public void initialize(final String ServersConfigFileName) {
        log.info("ServersConfigReader initialize: Read the sever settings from {}", ServersConfigFileName);

        JsonReader jsonReader = new JsonReader(ServersConfigFileName, false);

        //Get the server property
        serverAlias = System.getProperty(SERVER);
        log.info("Server Alias --> {}", serverAlias);


        baseUrl = jsonReader.getKeyValue(serverAlias, URL);
        log.info("Base URL --> {}", baseUrl);

    }

    /**
     * Gets the server alias.
     *
     * @return Server Alias.
     */
    public String getServerAlias() {
        return serverAlias;
    }

    /**
     * Gets the automation base URL.
     *
     * @return URL.
     */
    public String getURL() {
        return baseUrl;
    }
}
