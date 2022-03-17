package steps;

import config.UsersConfigReader;

import entities.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import ui.LoginPage;
import ui.PageTransporter;
import ui.ProfilePage;
import ui.HomePage;

import java.net.MalformedURLException;

import static org.testng.Assert.assertTrue;

public class loginSteps {
    private PageTransporter pageTransporter;
    private Logger log = Logger.getLogger(getClass());

    //Pages
    private LoginPage loginPage;
    private HomePage homePage;
    private ProfilePage profilePage;

    // Entities
    private User user;

    public loginSteps(User user) {
        this.pageTransporter = new PageTransporter();
        this.user = user;
    }

    //****************************************************************
    //Login Step Definitions
    //****************************************************************
    @Given("^I navigate to Login page$")
    public void iNavigateToLoginPage() throws MalformedURLException {
        //Use this step for login feature scenarios
        homePage = pageTransporter.navigateToHomePage();
        loginPage = homePage.goToLoginPage();
    }

    @When("^I login as \"(.*?)\" with password \"(.*?)\"$")
    public void iLoginAsWithPassword(String email, String password) {
        //Use this step for login feature scenarios
        profilePage = loginPage.login(email, password);
    }

    @And("^I go to Home page$")
    public void iGoToHomePage() {
        homePage = profilePage.goHomePage();
    }

    @Then("^I should login successfully with a \"(.*?)\"$")
    public void iShouldLoginSuccessfullyWithA(String fullName) {
        String actual = profilePage.getUserName(fullName);
        assertTrue(fullName.equals(actual), "full name the user is showed");

    }

    @Given("^I (?:am logged in|login) as \"(.*?)\" User$")
    public void loginAsUser(final String userAlias) {
        user = UsersConfigReader.getInstance().getUserByAlias(userAlias);
        iLoginAsWithPassword(user.getUserEmail(), user.getPassword());
    }
}

