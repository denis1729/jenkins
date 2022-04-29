package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import selenium.WebDriverManager;
import selenium.WebDriverTools;

public abstract class BasePage {
    protected Logger log = Logger.getLogger(getClass());
    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    protected WebDriverTools driverTools;

    protected WebDriverManager webDriverManager;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     */
    public BasePage(WebDriverManager webDriverManager) {
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
