package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import selenium.WebDriverManager;
import selenium.WebDriverTools;
import utils.LoggerSingleton;

public abstract class BasePage {
    protected final Logger log = LoggerSingleton.getInstance().getLogger(getClass().getName());
    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    protected WebDriverTools driverTools;

    protected WebDriverManager webDriverManager;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     */
    protected BasePage(WebDriverManager webDriverManager) {
        this.webDriverManager = webDriverManager;
        this.driver = webDriverManager.getWebDriver();
        this.wait = webDriverManager.getWait();
        this.driverTools = new WebDriverTools(webDriverManager);
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();

    }

    /**
     * Waits until page object is loaded.
     */
    public abstract void waitUntilPageObjectIsLoaded();

}
