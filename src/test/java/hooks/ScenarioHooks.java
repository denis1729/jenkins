package hooks;

import config.AutomationSFDC;

import entities.User;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import ui.HomePage;


public class ScenarioHooks {
    private Logger log = Logger.getLogger(getClass());
    private HomePage homePage;
    private User user;

    public ScenarioHooks(User user) {
        this.user = user;
    }

    @Before(order = 101)
    public void initializeConfiguration() {
        log.info("Initializing configuration before all hooks...");
        AutomationSFDC.getInstance().startUp();
    }

    //****************************************************************
    //Hooks for @Login scenarios
    //****************************************************************
    @After(value = "@Logout")
    public void logoutSession() {
        log.info("After hook @Login");
        homePage = new HomePage().logout();
        AutomationSFDC.getInstance().shutDown();

    }
}