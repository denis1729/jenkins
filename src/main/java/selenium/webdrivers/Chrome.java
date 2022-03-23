package selenium.webdrivers;

import config.ServerRemoteConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import selenium.entities.ServerRemote;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
/**
 * La clase Chrome es un navegador que extiende de la clase DriverAbstract.
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
class Chrome extends DriverAbstract {
    /**
     * Inicializa el Web Driver.
     *
     * @return Un ChromeDriver.
     */
    @Override
    public WebDriver initDriver() {
        WebDriverManager.chromedriver().setup();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");
        DesiredCapabilities capabilities = new DesiredCapabilities();//modo privado
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("incognito");//modo privado
        chromeOptions.addArguments("use-fake-ui-for-media-stream");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);//modo privado

        webDriver = new ChromeDriver(chromeOptions);

        return webDriver;
    }

    /**
     * Inicia el web driver remoto.
     *
     * @return WebDriver
     */
    @Override
    public WebDriver initRemoteDriver() {
        ServerRemote serverRemote = ServerRemoteConfigReader.getInstance().getServerByAlias("chrome");

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
