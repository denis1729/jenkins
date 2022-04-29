package ui;

import config.ServersConfigReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import selenium.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

public class PageTransporter {
    private final Logger log = Logger.getLogger(getClass());
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
     * @throws MalformedURLException Exception
     */
    private void goToURL(final String url) throws MalformedURLException {
        try {
            webDriver.manage().deleteAllCookies();
            webDriver.navigate().to(new URL(url));
        } catch (MalformedURLException e) {
            log.error("Could not go to URL", e);
            throw e;
        }
    }

    /**
     * Navigates to Login Page.
     *
     * @return New instance of LoginPage.
     * @throws MalformedURLException Exception
     */
    public HomePage navigateToHomePage() throws MalformedURLException {
        goToURL(baseURL);
        return new HomePage(this.webDriverManager);
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }
}
