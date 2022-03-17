package selenium.webdrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class FireFox implements IDriver {

    /**
     * Initializes FireFox driver.
     * @return A new FireFoxDriver.
     */
    @Override
    public WebDriver initDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
