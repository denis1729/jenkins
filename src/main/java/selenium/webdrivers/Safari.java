package selenium.webdrivers;

import config.ServerRemoteConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import selenium.entities.ServerRemote;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * La clase Safari es un navegador que extiende de la clase DriverAbstract.
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public class Safari extends DriverAbstract {
    /**
     * Inicializa el Web Driver.
     *
     * @return WebDriver.
     */
    @Override
    public WebDriver initDriver() {
        webDriver = new SafariDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return webDriver;
    }

    /**
     * Inicia el web driver remoto.
     *
     * @return WebDriver
     */
    @Override
    public WebDriver initRemoteDriver() {
        ServerRemote serverRemote = ServerRemoteConfigReader.getInstance().getServerByAlias("safari");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PLATFORM, serverRemote.getPlatform());
        caps.setCapability(PLATFORM_NAME, serverRemote.getPlatformName());
        caps.setCapability(BROWSER_NAME, serverRemote.getBrowserName());
        caps.setCapability(VERSION, serverRemote.getVersion());
        caps.setCapability(COMMAND_TIMEOUT, serverRemote.getCommandTimeout());

        try {
            webDriver = new RemoteWebDriver(new URL(serverRemote.getServerUrl()), caps);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return webDriver;
    }
}
