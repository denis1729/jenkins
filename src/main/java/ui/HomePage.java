package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;

public class HomePage extends BasePage {
    @FindBy(xpath = "//*[@class='login']")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@class='logout']")
    private WebElement logoutBtn;

    @FindBy(xpath = "//*[@id='block_top_menu']//following::a[@title='Women']")
    private WebElement womenBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager driver
     */
    public HomePage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    /**
     * Waits until page object is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        log.info("Into the home page");
    }

    private LoginPage clickLoginButton() {
        driverTools.clickElement(loginBtn);
        return new LoginPage(this.webDriverManager);
    }

    private void clickLogoutButton() {
        if (driverTools.isElementDisplayed(logoutBtn))
            driverTools.clickElement(logoutBtn);
    }

    private void clickWomenPage() {
        driverTools.clickElement(womenBtn);
    }

    public LoginPage goToLoginPage() {
        return clickLoginButton();
    }

    public void logout() {
        clickLogoutButton();
    }

    public void goToWomenPage() {
        clickWomenPage();
    }
}
