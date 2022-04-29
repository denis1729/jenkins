package hooks;

import config.AutomationSFDC;

import entities.Container;
import entities.User;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.apache.log4j.Logger;
import ui.HomePage;


public class ScenarioHooks {
    private Logger log = Logger.getLogger(getClass());
    private HomePage homePage;
    private Container container;

    public ScenarioHooks(Container container) {
        this.container = container;
    }

    @Before(order = 101)
    public void initializeConfiguration() {
        log.info("Initializing configuration before all hooks...");
        AutomationSFDC.getInstance();
    }
}