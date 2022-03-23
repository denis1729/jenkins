package selenium.webdrivers;

import org.openqa.selenium.WebDriver;
/**
 * La interfaz IDriver inicia el web driver de forma local y remota.
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
interface IDriver {

    /**
     * Inicializa el Web Driver.
     *
     * @return WebDriver.
     */
    WebDriver initDriver();

    /**
     * Inicia el web driver remoto.
     * @return WebDriver
     */
    WebDriver initRemoteDriver();
}
