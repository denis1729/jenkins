package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;

public class ProfilePage extends BasePage {
    @FindBy(xpath = "//*[@class='home']")
    private WebElement homeBtn;

    @FindBy(xpath = "//*[@class='account']")
    private WebElement userNameLabel;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager driver
     */
    public ProfilePage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    /**
     * Waits until page object is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        log.info("Into the profile page");
    }

    private HomePage clickHomeButton() {
        driverTools.clickElement(homeBtn);
        return new HomePage(this.webDriverManager);
    }

    public HomePage goHomePage() {
        return clickHomeButton();
    }

    public String getUserName(String userName) {
        return driverTools.getElementText(userNameLabel);
    }
}
