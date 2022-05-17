package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import utils.MergeReports;
import utils.PathReader;


@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"steps", "hooks"},
        plugin = {"json:target/login.json",
                "pretty"
        },
        monochrome = true,
        tags = "@login")

public class TestLogin extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterTest
    public void generateReport() {
        String pathCucumberFiles = PathReader.getPathLocal("target");
        String pathCucumberFilesReport = PathReader.getPathLocal("target_1");
        String pathOutput = PathReader.getPathLocal("cucumber-report");
        MergeReports.generateMergeReport(pathCucumberFiles, pathCucumberFilesReport, pathOutput);
    }

}
