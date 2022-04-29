package steps;

import config.AutomationSFDC;
import config.UsersConfigReader;
import entities.Container;
import entities.User;
import io.cucumber.java.After;
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
    private Container container;

    public LoginSteps(Container container) {
        this.container = container;
        this.pageTransporter = new PageTransporter();
        this.container.pageTransporter = pageTransporter;
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
        container.homePage = homePage;
    }

    @When("^I login as \"(.*?)\" with password \"(.*?)\"$")
    public void i_login_as_with_password(String email, String password) {
        //Use this step for login feature scenarios
        profilePage = loginPage.login(email, password);
    }

    @Given("^I (?:am logged in|login) as \"(.*?)\" User$")
    public void loginAsUser(final String userAlias) {
        container.user = UsersConfigReader.getInstance().getUserByAlias(userAlias);
        i_login_as_with_password(container.user.getUserEmail(), container.user.getPassword());
    }

    @Then("^I should login successfully with a \"(.*?)\"$")
    public void i_should_login_successfully_with_a(String fullName) {
        String actual = profilePage.getUserName(fullName);
        log.info(String.format("data %s and %s", fullName, actual));
        assertTrue(fullName.equalsIgnoreCase(actual), "full name the user is showed");
    }


    //****************************************************************
    //Hooks for @Login scenarios
    //****************************************************************
    @After(value = "@Logout")
    public void logoutSession() {
        log.info("After hook @Login");
        homePage.logout();
        pageTransporter.getWebDriverManager().quitDriver();
    }
}
