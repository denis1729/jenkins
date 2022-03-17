package selenium.webdrivers;

import org.openqa.selenium.WebDriver;

interface IDriver {

    /**
     * Initializes the Web Driver.
     *
     * @return WebDriver.
     */
    WebDriver initDriver();
}
