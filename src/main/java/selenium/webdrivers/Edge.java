package selenium.webdrivers;

import config.ServerRemoteConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import selenium.entities.ServerRemote;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * La clase Edge es un navegador que extiende de la clase DriverAbstract.
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public class Edge extends DriverAbstract{
    /**
     * Inicializa el Web Driver.
     *
     * @return Un EdgeDriver.
     */
    @Override
    public WebDriver initDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    /**
     * Inicia el web driver remoto.
     * @return WebDriver
     */
    @Override
    public WebDriver initRemoteDriver() {
        ServerRemote serverRemote = ServerRemoteConfigReader.getInstance().getServerByAlias("edge");

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
