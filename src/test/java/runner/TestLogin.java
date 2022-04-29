package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"steps", "hooks"},
        plugin = {"json:cucumber/login.json",
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        tags = "@login")

public class TestLogin extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }

}