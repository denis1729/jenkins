 package utils;

import java.io.File;

/**
 * La clase PathReader es un utilitario que nos facilita la lectura PATHS.
 *
 * @author Denis Camacho Camacho
 * @since 12/27/2021
 */
public class PathReader {

    private PathReader() {

    }

    private static final String PATH_LOCAL = System.getProperty("user.dir");

    private static final String PATH_CONFIG = PATH_LOCAL + File.separator + "config" +
            File.separator;

    public static String getPathConfig() {
        return PATH_CONFIG;
    }

    public static String getPathLocal(String dir) {
        return PATH_LOCAL + File.separator + dir + File.separator;
    }

}
