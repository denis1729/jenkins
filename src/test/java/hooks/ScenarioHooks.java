package hooks;

import config.AutomationConfig;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import utils.LoggerSingleton;


public class ScenarioHooks {
    protected final Logger log = LoggerSingleton.getInstance().getLogger(getClass().getName());

    public ScenarioHooks() {
    }

    @Before(order = 101)
    public void initializeConfiguration() {
        log.info("Initializing configuration before all hooks...");
        AutomationConfig.getInstance();
    }
}