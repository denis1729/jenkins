package selenium.webdrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;

class Chrome implements IDriver {
    /**
     * Initializes Chrome driver.
     *
     * @return A new ChromeDriver.
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

        return new ChromeDriver(chromeOptions);
    }


}
