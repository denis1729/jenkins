package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import selenium.WebDriverManager;

public class LoginPage extends BasePage {
    @FindBy(id = "email")
    private WebElement emailTxt;

    @FindBy(id = "email_create")
    private WebElement emailCreateTxt;

    @FindBy(id = "passwd")
    private WebElement passwordTxt;

    @FindBy(id = "SubmitLogin")
    private WebElement loginBtn;

    @FindBy(id = "SubmitCreate")
    private WebElement createAccountBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager web
     */
    public LoginPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(loginBtn));
    }

    /**
     * Set the email for create an account
     *
     * @param email the user email
     */
    public void setEmailCreate(final String email) {
        driverTools.setInputField(emailCreateTxt, email);
    }

    /**
     * Logs in to Salesforce
     *
     * @param email    - The user email
     * @param password - The password
     * @return ProfilePage
     */
    public ProfilePage login(final String email, final String password) {
        driverTools.setInputField(emailTxt, email);
        driverTools.setInputField(passwordTxt, password);
        driverTools.clickElement(loginBtn);
        return new ProfilePage(this.webDriverManager);
    }
}
