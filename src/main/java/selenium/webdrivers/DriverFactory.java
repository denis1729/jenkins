package selenium.webdrivers;

import org.openqa.selenium.WebDriver;
import selenium.WebDriverConfigReader;

/**
 * La clase DriverFactory tiene la capacidad de crear un tipo de navegador según el usuario lo requiera.
 *
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public class DriverFactory {

    private static final String FIREFOX = "firefox";
    private static final String EDGE = "edge";
    private static final String SAFARI = "safari";

    private DriverFactory() {
    }

    /**
     * Retorna una instancia de un navegador.
     *
     * @return instancia de web driver .
     */
    public static WebDriver getDriver() {
        IDriver browser;
        switch (WebDriverConfigReader.getInstance().getBrowser().toLowerCase()) {
            case FIREFOX:
                browser = new FireFox();
                break;
            case EDGE:
                browser = new Edge();
                break;
            case SAFARI:
                browser = new Safari();
                break;
            default:
                browser = new Chrome();
                break;
        }
        return WebDriverConfigReader.getInstance().getRemote() ? browser.initRemoteDriver() : browser.initDriver();
    }
}
