package utils;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.slf4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GenerateReportCucumber {
    private static final Logger log = LoggerSingleton.getInstance().getLogger("GenerateReportCucumber");

    private GenerateReportCucumber() {
    }

    public static void generateReportsCucumber(String pathCucumberFiles, String pathReportOutput) {
        log.info("Generando reporte ........................................");

        File rd = new File(pathCucumberFiles);
        if (rd.exists()) {
            List<String> jsonFiles = new ArrayList<>();
            File outputReport = new File(pathReportOutput);
            for (File file : Objects.requireNonNull(rd.listFiles())) {
                if (!file.isDirectory())
                    jsonFiles.add(pathCucumberFiles + file.getName());
            }
            Configuration configuration = new Configuration(outputReport, "cucumber-report");
            configuration.setBuildNumber("1");
            ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
            reportBuilder.generateReports();
            log.info("Reporte cucumber se guardo en la direccion: \n{}", outputReport.getAbsolutePath());
        }
    }
}
