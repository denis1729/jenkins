package steps;

import config.UsersConfigReader;
import entities.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import ui.HomePage;
import ui.LoginPage;
import ui.PageTransporter;
import ui.ProfilePage;

import java.net.MalformedURLException;

import static org.testng.Assert.assertTrue;

public class LoginSteps {
    private final PageTransporter pageTransporter;
    private final Logger log = Logger.getLogger(getClass());

    //Pages
    private LoginPage loginPage;
    private HomePage homePage;
    private ProfilePage profilePage;

    // Entities
    private User user;

    public LoginSteps(User user) {
        this.pageTransporter = new PageTransporter();
        this.user = user;
    }

    @Given("^I navigate to Login page$")
    public void i_navigate_to_login_page() throws MalformedURLException {
        //Use this step for login feature scenarios
        homePage = pageTransporter.navigateToHomePage();
        loginPage = homePage.goToLoginPage();
    }

    @And("^I go to Home page$")
    public void iGoToHomePage() {
        homePage = profilePage.goHomePage();
    }

    @When("^I login as \"(.*?)\" with password \"(.*?)\"$")
    public void i_login_as_with_password(String email, String password) {
        //Use this step for login feature scenarios
        profilePage = loginPage.login(email, password);
    }

    @Given("^I (?:am logged in|login) as \"(.*?)\" User$")
    public void loginAsUser(final String userAlias) {
        user = UsersConfigReader.getInstance().getUserByAlias(userAlias);
        i_login_as_with_password(user.getUserEmail(), user.getPassword());
    }

    @Then("^I should login successfully with a \"(.*?)\"$")
    public void i_should_login_successfully_with_a(String fullName) {
        String actual = profilePage.getUserName(fullName);
        log.info(String.format("data %s and %s", fullName, actual));
        assertTrue(fullName.equalsIgnoreCase(actual), "full name the user is showed");
    }
}
