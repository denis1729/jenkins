package config;

import org.apache.log4j.Logger;
import utils.JsonReader;

/**
 * La clase EnvironmentConfigReader lee el archivo de configuración para configurar el entorno en el que se trabajara.
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public class EnvironmentConfigReader {
    private org.apache.log4j.Logger log = Logger.getLogger(getClass());

    //web
    private static final String SERVER = "server";
    private static final String BROWSER = "browser";
    private static final String REMOTE = "remote";

    private static EnvironmentConfigReader instance;

    /**
     * Retorna la instancia de EnvironmentConfigReader.
     *
     * @return la instancia de EnvironmentConfigReader.
     */
    public static EnvironmentConfigReader getInstance() {
        if (instance == null) {
            instance = new EnvironmentConfigReader();
        }
        return instance;
    }

    /**
     * Lee los valores del archivo de configuracion json.
     */
    public void initialize(String environmentConfigFileName) {
        log.info("ServersConfigReader initialize: Read the sever settings from " + environmentConfigFileName);

        JsonReader jsonReader = new JsonReader(environmentConfigFileName,false);

        System.setProperty(BROWSER, jsonReader.getKeyValue(BROWSER));
        System.setProperty(SERVER, jsonReader.getKeyValue(SERVER));
        System.setProperty(REMOTE, jsonReader.getKeyValue(REMOTE));
    }
}
