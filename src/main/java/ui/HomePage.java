package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;

/**
 * The HomePage class represents the main page.
 *
 * @author Denis Camacho Camacho
 * @since 09/09/2022
 */
public class HomePage extends BasePage {
    @FindBy(css = "*.login")
    private WebElement loginBtn;

    @FindBy(xpath = "*.logout")
    private WebElement logoutBtn;

    @FindBy(css = ".sf-menu>*>[title='Women']")
    private WebElement womenSection;

    @FindBy(css = ".sf-menu>*>[title='Dresses']")
    private WebElement dressesSection;

    @FindBy(css = ".sf-menu>*>[title='T-shirts']")
    private WebElement tShirtsSection;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager web driver
     */
    public HomePage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    /**
     * Waits until page object is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        log.info("Into the page " + getClass().getName());
    }

    /**
     * This method performs click on the login button
     *
     * @return the login page
     */
    public LoginPage goToLoginPage() {
        driverTools.clickElement(loginBtn);
        return new LoginPage(this.webDriverManager);
    }

    /**
     * This method performs click on the logout button
     */
    public void logout() {
        if (driverTools.isElementDisplayed(logoutBtn))
            driverTools.clickElement(logoutBtn);
    }

    public void goToWomenPage() {
        driverTools.clickElement(womenSection);
    }
}
