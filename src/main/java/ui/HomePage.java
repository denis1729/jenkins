package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//*[@class='login']")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[@class='logout']")
    private WebElement logoutBtn;

    @FindBy(xpath = "//*[@id='block_top_menu']//following::a[@title='Women']")
    private WebElement womenBtn;

    /**
     * Waits until page object is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {

    }

    private LoginPage clickLoginButton() {
        driverTools.clickElement(loginBtn);
        return new LoginPage();
    }

    private HomePage clickLogoutButton() {
        driverTools.clickElement(logoutBtn);
        return new HomePage();
    }

    private void clickWomenPage() {
        driverTools.clickElement(womenBtn);
    }

    public LoginPage goToLoginPage() {
        return clickLoginButton();
    }

    public HomePage logout() {
        return clickLogoutButton();
    }

    public void goToWomenPage() {
        clickWomenPage();
    }
}
