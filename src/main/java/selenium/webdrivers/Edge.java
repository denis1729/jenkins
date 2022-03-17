package selenium.webdrivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Edge implements IDriver{
    /**
     * Initializes the Web Driver.
     *
     * @return WebDriver.
     */
    @Override
    public WebDriver initDriver() {
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }
}
