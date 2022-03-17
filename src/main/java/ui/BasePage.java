package ui;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.WebDriverManager;
import selenium.WebDriverTools;

public abstract class BasePage {
    protected Logger log = Logger.getLogger(getClass());
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverTools driverTools;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     */
    public BasePage() {
        this.driver = WebDriverManager.getInstance().getWebDriver();
        this.wait = WebDriverManager.getInstance().getWait();
        this.driverTools = new WebDriverTools();
        PageFactory.initElements(driver, this);
        waitUntilPageObjectIsLoaded();

    }

    /**
     * Waits until page object is loaded.
     */
    public abstract void waitUntilPageObjectIsLoaded();

}
