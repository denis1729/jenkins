package ui.popup;

import selenium.WebDriverManager;
import ui.HomePage;

public abstract class PopupPage extends HomePage {
    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager driver
     */
    protected PopupPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }
}
