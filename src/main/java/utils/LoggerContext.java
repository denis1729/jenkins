package utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * La clase LoggerSingleton es un utilitario que nos facilita el registro de eventos en las clases y métodos.
 *
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public class LoggerContext {
    private static LoggerContext instance;
    private String className;
    private Logger logger;
    private ConfigurationBuilder<BuiltConfiguration> builder;
    private AppenderComponentBuilder file;
    private static final String FILE_NAME = "fileName";
    private static final String LOG_PATH = "logs/log.log";

    private LoggerContext() {
        setFileName();
    }

    public static LoggerContext getInstance() {
        if (instance == null)
            instance = new LoggerContext();
        return instance;
    }

    private void setFileName() {
        builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        //console
        AppenderComponentBuilder console = builder.newAppender("console", "Console");
        LayoutComponentBuilder standard
                = builder.newLayout("PatternLayout");
        standard.addAttribute("pattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
        console.add(standard);
        builder.add(console);
        //
        AppenderComponentBuilder rolling = builder.newAppender("fileLogger", "RollingFile");
        rolling.addAttribute(FILE_NAME, LOG_PATH);
        rolling.addAttribute("filePattern", "logs/logs%d{yyyyMMdd}.log");
        rolling.add(standard);

        file = builder.newAppender("fileLog", "File");
        file.addAttribute(FILE_NAME, LOG_PATH);
        file.add(standard);
        builder.add(file);

        RootLoggerComponentBuilder rootLogger
                = builder.newRootLogger(Level.INFO);
        rootLogger.add(builder.newAppenderRef("console"));
        rootLogger.add(builder.newAppenderRef("fileLog"));
        builder.add(rootLogger);

        ComponentBuilder triggeringPolicies = builder.newComponent("Policies")
                .addComponent(builder.newComponent("SizeBasedTriggeringPolicy")
                        .addAttribute("size", "100MB"))
                .addComponent(builder.newComponent("TimeBasedTriggeringPolicy")
                        .addAttribute("interval", "1")).addAttribute("interval", "true")
                .addComponent(builder.newComponent("DefaultRolloverStrategy"))
                .addComponent(builder.newComponent("Delete")
                        .addAttribute("basePath", "logs/")
                        .addAttribute("maxDepth", "10"))
                .addComponent(builder.newComponent("IfLastModified")
                        .addAttribute("age", "30d"));

        rolling.addComponent(triggeringPolicies);

        Configurator.initialize(builder.build());
    }

    /**
     * Cambia el archivo de registro del log
     *
     * @param fileName nombre del archivo
     */
    public void setLogger(String fileName) {
        file.addAttribute(FILE_NAME, String.format("logs/%s.log", fileName));
        builder.add(file);
        Configurator.reconfigure(builder.build());
        logger = LoggerFactory.getLogger(this.className);
    }

    /**
     * Cambia el archivo de registro del log
     */
    public void setDefaulLogger() {
        file.addAttribute(FILE_NAME, LOG_PATH);
        builder.add(file);
        Configurator.reconfigure(builder.build());
        logger = LoggerFactory.getLogger(this.className);
    }

    /**
     * Este metodo retorna el Logger
     *
     * @param className nombre de la clase
     * @return logger
     */
    public Logger getLogger(String className) {
        this.className = className;
        logger = LoggerFactory.getLogger(className);
        return logger;
    }
}
