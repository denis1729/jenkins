package selenium.webdrivers;

import config.ServerRemoteConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import selenium.entities.ServerRemote;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * La clase FireFox es un navegador que extiende de la clase DriverAbstract.
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
class FireFox extends DriverAbstract {

    /**
     * Inicializa el Web Driver.
     * @return Un FireFoxDriver.
     */
    @Override
    public WebDriver initDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    /**
     * Inicia el web driver remoto.
     * @return WebDriver
     */
    @Override
    public WebDriver initRemoteDriver() {
        ServerRemote serverRemote = ServerRemoteConfigReader.getInstance().getServerByAlias("firefox");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PLATFORM, serverRemote.getPlatform());
        caps.setCapability(PLATFORM_NAME, serverRemote.getPlatformName());
        caps.setCapability(BROWSER_NAME, serverRemote.getBrowserName());
        caps.setCapability(VERSION, serverRemote.getVersion());
        caps.setCapability(COMMAND_TIMEOUT, serverRemote.getCommandTimeout());

        try {
            webDriver = new RemoteWebDriver(new URL(serverRemote.getServerUrl()), caps);
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return webDriver;
    }
}
