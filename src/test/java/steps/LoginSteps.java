package steps;

import config.UsersConfigReader;
import entities.Container;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.awaitility.Awaitility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import ui.HomePage;
import ui.LoginPage;
import ui.PageTransporter;
import ui.ProfilePage;
import utils.LoggerSingleton;

import java.time.Duration;

import static org.testng.Assert.*;

public class LoginSteps {
    private final PageTransporter pageTransporter;
    private final Logger log = LoggerSingleton.getInstance().getLogger(getClass().getName());

    //Pages
    private LoginPage loginPage;
    private HomePage homePage;
    private ProfilePage profilePage;

    // Entities
    private final Container container;

    public LoginSteps(Container container) {
        this.container = container;
        this.pageTransporter = new PageTransporter();
        this.container.pageTransporter = pageTransporter;
    }

    @Given("^I navigate to Login page$")
    public void i_navigate_to_login_page() {
        homePage = pageTransporter.navigateToHomePage();
        loginPage = homePage.goToLoginPage();
    }

    @And("^I go to Home page$")
    public void iGoToHomePage() {
        homePage = profilePage.goHomePage();
        container.homePage = homePage;
    }

    @When("^I login as (.*?) with password (.*?)$")
    public void i_login_as_with_password(String email, String password) {
        //Use this step for login feature scenarios
        profilePage = loginPage.login(email, password);
    }

    @Given("^I (?:am logged in|login) as (.*?) User$")
    public void loginAsUser(final String userAlias) {
        container.user = UsersConfigReader.getInstance().getUserByAlias(userAlias);
        i_login_as_with_password(container.user.getUserEmail(), container.user.getPassword());
    }

    @Then("^I should login successfully with a (.*?)$")
    public void i_should_login_successfully_with_a(String fullName) {
        String actual = profilePage.getUserName();
        log.info("Expected user name: {} and actual user name:{}", fullName, actual);
        assertEquals(actual, fullName, "full name the user is showed");
    }

    @Then("^The following alert should be display \"(.*?)\"$")
    public void the_following_alert_display(String alert) {
        String actual = loginPage.getAlertMessage();
        log.info("Actual alert: {} and expected alert :{}", alert, actual);
        assertEquals(actual, alert, "The message alert is not equals");
    }

    @Then("^I shouldn't login and show the following message \"(.*?)\"$")
    public void i_no_should_login__with_a(String message) {
        String actual = loginPage.getErrorMessage();
        log.info("Actual error: {} and expected error {}", message, actual);
        assertEquals(actual, message, "The error message is not equals");
    }


    //****************************************************************
    //Hooks for @Login scenarios
    //****************************************************************
    @After(order = 150)  //order: mientras mas mayor sea, primero se ejecutara
    public void logoutSession(Scenario scenario) {
        log.info("Ejecutando After hook logout");
        int timeWait = 500;//milisegundos
        if (scenario.isFailed())
            log.error("FALLO el scenario {}", scenario.getName());
        else
            log.info("scenario {} EXITOSO", scenario.getName());

        JavascriptExecutor jse = (JavascriptExecutor) pageTransporter.getWebDriverManager().getWebDriver();
        jse.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
        Awaitility.await().pollDelay(Duration.ofMillis(timeWait)).until(() -> true);
        byte[] screenshot = ((TakesScreenshot) pageTransporter.getWebDriverManager().getWebDriver()).
                getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "scenarioup");
        jse.executeScript("window.scrollTo(0 , document.body.scrollHeight/3)");
        Awaitility.await().pollDelay(Duration.ofMillis(timeWait)).until(() -> true);
        byte[] screenshot1 = ((TakesScreenshot) pageTransporter.getWebDriverManager().getWebDriver()).
                getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot1, "image/png", "scenariocenter");
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Awaitility.await().pollDelay(Duration.ofMillis(timeWait)).until(() -> true);
        byte[] screenshot2 = ((TakesScreenshot) pageTransporter.getWebDriverManager().getWebDriver()).
                getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot2, "image/png", "scenariodown");
        pageTransporter.getWebDriverManager().quitDriver();
    }
}
