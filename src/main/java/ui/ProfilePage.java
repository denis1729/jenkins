package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePage extends BasePage {
    @FindBy(xpath = "//*[@class='home']")
    private WebElement homeBtn;

    @FindBy(xpath = "//*[@class='account']")
    private WebElement userNameLabel;

    /**
     * Waits until page object is loaded.
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {

    }

    private HomePage clickHomeButton() {
        driverTools.clickElement(homeBtn);
        return new HomePage();
    }

    public HomePage goHomePage() {
        return clickHomeButton();
    }

    public String getUserName(String userName) {
        return driverTools.getElementText(userNameLabel);
    }
}
