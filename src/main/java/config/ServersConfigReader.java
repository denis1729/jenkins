package config;

import org.apache.log4j.Logger;
import utils.JsonReader;

public final class ServersConfigReader {
    private Logger log = Logger.getLogger(getClass());

    //web
    private static final String SERVER = "server";
    private static final String URL = "url";
    private final String SERVICE_BASE_URI = "service base uri";
    private final String TOKEN_BASE_URI = "token base uri";


    private String server;
    private String serverAlias;
    private String url;
    private String serviceBaseUri;
    private String tokenBaseUri;

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
        log.info("ServersConfigReader initialize: Read the sever settings from " + ServersConfigFileName);

        JsonReader jsonReader = new JsonReader(ServersConfigFileName,false);

        //Get the server property
        serverAlias = System.getProperty(SERVER);
        log.info("Server Alias --> " + serverAlias);


        url = jsonReader.getKeyValue(serverAlias, URL);
        log.info("Base URL --> " + url);

        serviceBaseUri = jsonReader.getKeyValue(serverAlias, SERVICE_BASE_URI);
        log.info("Service Base URI --> " + serviceBaseUri);

        tokenBaseUri = jsonReader.getKeyValue(serverAlias, TOKEN_BASE_URI);
        log.info("Token Base URI --> " + tokenBaseUri);

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
     * Gets the server host name or IP.
     *
     * @return Server.
     */
    public String getServer() {
        return server;
    }

    /**
     * Gets the SFDC base URL.
     *
     * @return URL.
     */
    public String getURL() {
        return url;
    }


    /**
     * Gets..
     * @return
     */
    public String getServiceBaseUri() {
        return serviceBaseUri;
    }

    /**
     *
     * @return
     */
    public String getTokenBaseUri() {
        return tokenBaseUri;
    }
}
