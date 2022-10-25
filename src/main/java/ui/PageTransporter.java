package ui;

import config.ServersConfigReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import selenium.WebDriverManager;
import utils.LoggerSingleton;

import java.net.MalformedURLException;
import java.net.URL;

public class PageTransporter {
    private final Logger log = LoggerSingleton.getInstance().getLogger(getClass().getName());
    private final String baseURL;
    private final WebDriver webDriver;
    private final WebDriverManager webDriverManager;


    /**
     * Constructor of page transporter.
     */
    public PageTransporter() {
        log.info("Initialize the page transporter");
        this.baseURL = ServersConfigReader.getInstance().getURL();
        this.webDriverManager = new WebDriverManager();
        this.webDriver = webDriverManager.getWebDriver();
    }


    /**
     * Goes to the given URL.
     *
     * @param url - Site's URL.
     */
    private void goToURL(final String url) {
        try {
            webDriver.manage().deleteAllCookies();
            webDriver.navigate().to(new URL(url));
        } catch (MalformedURLException e) {
            log.error("Could not go to URL", e);
        }
    }

    /**
     * Navigates to Login Page.
     *
     * @return New instance of LoginPage.
     */
    public HomePage navigateToHomePage() {
        goToURL(baseURL);
        return new HomePage(this.webDriverManager);
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }
}
