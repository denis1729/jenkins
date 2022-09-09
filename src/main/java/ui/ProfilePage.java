package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;

public class ProfilePage extends BasePage {
    @FindBy(css = "*.home")
    private WebElement homeBtn;

    @FindBy(css = "*.account")
    private WebElement userNameLabel;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager web
     */
    public ProfilePage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    /**
     * Waits until page object is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        log.info("Into the page : " + getClass().getName());
    }

    public HomePage goHomePage() {
        driverTools.clickElement(homeBtn);
        return new HomePage(this.webDriverManager);
    }

    public String getUserName() {
        return driverTools.getElementText(userNameLabel);
    }
}
