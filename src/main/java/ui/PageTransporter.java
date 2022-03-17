package ui;
import config.ServersConfigReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import selenium.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

public class PageTransporter {
    private Logger log = Logger.getLogger(getClass());
    private String baseURL = ServersConfigReader.getInstance().getURL();
    private WebDriver webDriver = WebDriverManager.getInstance().getWebDriver();
    private static PageTransporter instance;

    /**
     * Constructor of page transporter.
     */
    public PageTransporter() {
        initialize();
    }

    /**
     * Initializes page transporter.
     */
    private void initialize() {
        log.info("Initialize the page transporter");
    }

    /**
     * Goes to the given URL.
     *
     * @param url - Site's URL.
     * @throws MalformedURLException Exception
     */
    private void goToURL(final String url) throws MalformedURLException {
        try {
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
        return new HomePage();
    }
}
