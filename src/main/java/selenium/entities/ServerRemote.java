package selenium.entities;

/**
 * La clase ServerRemote contiene información que se requiere para el browser se ejecute en modo remoto.
 * @author Denis Camacho Camacho
 * @since 10/20/2021
 */
public class ServerRemote {
    private String serverUrl = "";
    private String platform = "";
    private String platformName = "";
    private String browserName = "";
    private String version = "";

    private int commandTimeout = 0;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getCommandTimeout() {
        return commandTimeout;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }
}
