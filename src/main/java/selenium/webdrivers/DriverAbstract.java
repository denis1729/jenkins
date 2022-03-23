package selenium.webdrivers;

import org.openqa.selenium.WebDriver;
/**
 * La clase DriverAbstract implementa los métodos de la interfaz IDriver.
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public abstract class DriverAbstract implements IDriver {
    protected static  final String PLATFORM = "platform";
    protected static  final String PLATFORM_NAME = "platformName";
    protected static  final String BROWSER_NAME = "browserName";
    protected static  final String VERSION = "version";
    protected static  final String COMMAND_TIMEOUT = "commandTimeout";
    protected WebDriver webDriver;
}
