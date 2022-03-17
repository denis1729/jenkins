package ui;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
     * {@inheritDoc}
     */
    @Override
    public void waitUntilPageObjectIsLoaded() {
        wait.until(ExpectedConditions.visibilityOf(loginBtn));
    }

    /**
     * Sets the user email.
     *
     * @param email - the user email.
     */
    private void setEmail(final String email) {
        driverTools.setInputField(emailTxt, email);
    }

    /**
     * Sets the user password.
     *
     * @param password - The user password.
     */
    private void setPassword(final String password) {
        driverTools.setInputField(passwordTxt, password);
    }

    /**
     * Set the email for create an account
     *
     * @param email the user email
     */
    private void setEmailCreate(final String email) {
        driverTools.setInputField(emailCreateTxt, email);
    }

    /**
     * Clicks on login button.
     *
     * @return a list page.
     */
    private ProfilePage clickLoginButton() {
        driverTools.clickElement(loginBtn);
        return new ProfilePage();
    }

    /**
     * Logs in to Salesforce
     *
     * @param email    - The user email
     * @param password - The password
     * @return ProfilePage
     */
    public ProfilePage login(final String email, final String password) {
        setEmail(email);
        setPassword(password);
        return clickLoginButton();
    }
}
