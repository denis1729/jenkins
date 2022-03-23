package config;

import org.apache.log4j.Logger;
import selenium.entities.ServerRemote;
import utils.JsonReader;

import java.util.HashMap;
import java.util.Map;

/**
 * La clase ServerRemoteConfigReader crea clases de tipo ServerRemote a partir de un archivo de configuración json.
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public class ServerRemoteConfigReader {
    private Logger log = Logger.getLogger(getClass());
    private static final String SERVER_URL = "serverUrl";
    private static final String PLATFORM = "platform";
    private static final String PLATFORM_NAME = "platformName";
    private static final String BROWSER_NAME = "browserName";
    private static final String VERSION = "version";
    private static final String COMMAND_TIMEOUT = "commandTimeout";

    private HashMap<String, ServerRemote> servers;

    private static ServerRemoteConfigReader instance;


    /**
     * Retorna la instancia de ServerRemoteConfigReader.
     *
     * @return la instancia de ServerRemoteConfigReader.
     */
    public static ServerRemoteConfigReader getInstance() {
        if (instance == null) {
            instance = new ServerRemoteConfigReader();
        }
        return instance;
    }

    /**
     * Lee los valores del archivo de configuracion json.
     */
    public void initialize(String serverRemoteFileName) {
        log.info("UsersConfigReader initialize: Read the users settings from " + serverRemoteFileName);

        Map<String, Map<String, String>> dataRemoteServer = new JsonReader(serverRemoteFileName).getJsonObjectMain();

        servers = new HashMap<>();
        dataRemoteServer.forEach((alias, data) -> {
            ServerRemote serverRemote = new ServerRemote();
            serverRemote.setServerUrl(data.get(SERVER_URL));
            serverRemote.setBrowserName(data.get(BROWSER_NAME));
            serverRemote.setPlatform(data.get(PLATFORM));
            serverRemote.setPlatformName(data.get(PLATFORM_NAME));
            serverRemote.setVersion(data.get(VERSION));
            serverRemote.setCommandTimeout(Integer.parseInt(data.get(COMMAND_TIMEOUT)));
            servers.put(alias, serverRemote);
        });
    }

    /**
     * Devuelve un servidor remoto por alias.
     *
     * @param serverAlias - El alias del servidor.
     * @return un servidor.
     */
    public ServerRemote getServerByAlias(String serverAlias) {
        return servers.get(serverAlias);
    }
}
