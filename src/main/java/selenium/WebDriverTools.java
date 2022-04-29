package selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class to manage web driver tools.
 *
 * @author Silvia Valencia
 * @since 2/2/2018
 */
public class WebDriverTools {
    private final WebDriver driver;
    private Wait<WebDriver> wait;
    private static final By CHK_BOX = By.cssSelector("input[type='checkbox']");
    private final Logger log;

    /**
     * Generic checkbox to select or clear.
     */
    private static final By GENERIC_CHKBOX_BY = By.cssSelector("input[type='checkbox']");

    /**
     * Constructor.
     */
    public WebDriverTools(WebDriverManager webDriverManager) {
        log = Logger.getLogger(getClass());
        this.driver = webDriverManager.getWebDriver();
        this.wait = webDriverManager.getWait();
    }

    /**
     * Refresca la pagina actual.
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Permite realizar una pausa.
     *
     * @param millSeconds - tiempo de pausa en milliseconds.
     */
    public void sleepMilliSeconds(int millSeconds) {
        try {
            Thread.sleep(millSeconds);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Permite realizar una pausa.
     *
     * @param seconds - tiempo de pausa en segundos.
     */
    public void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Refresca la pagina hasta que se muestre el elemento.
     *
     * @param by             - Selector a esperar.
     * @param attemptsNumber - Numero de retrasos.
     * @param intervalTime   - Intervalo de tiempo en Milliseconds.
     * @return True si se encuentra el elemento sino false.
     */
    public boolean refreshPageUntilElementIsPresent(By by, int attemptsNumber, int intervalTime) {
        boolean found = false;
        int attempt = 1;
        while (!found && attempt <= attemptsNumber) {
            found = isElementDisplayed(by);
            if (!found) {
                refreshPage();
                sleepMilliSeconds(intervalTime);
            }
            attempt++;
        }

        return found;
    }

    /**
     * Realiza un click sobre el boton y espera hasta que aparesca el elmento a seleccionar.
     *
     * @param by             - elemento a esperar.
     * @param buttonSelector - elemento al que se realiza click.
     * @param attemptsNumber - numero de intento para realizar el click.
     * @param intervalTime   - intervalo de tiempo en millisecond.
     * @return True if element is displayed, false otherwise.
     */
    public boolean clickOnButtonUntilElementIsDisplayed(By by, By buttonSelector, int attemptsNumber,
                                                        int intervalTime) {
        int attempt = 1;
        boolean found = false;
        while (!found && attempt <= attemptsNumber) {
            found = isElementDisplayed(by, intervalTime);
            if (!found) {
                clickElement(buttonSelector);
            }
            attempt++;
        }
        return found;
    }

    /**
     * Espera hasta que el WebElement sea visible y luego elimina su contenido.
     *
     * @param webElement WebElement a esperar y limpiar
     */
    public void clearTextField(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
    }

    /**
     * Envia el contenido de text al WebElement.
     *
     * @param webElement - Tipo de entrada WebElement.
     * @param text       - Contenido para el WebElement.
     */
    public void setInputField(WebElement webElement, String text) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        clearTextField(webElement);
        webElement.sendKeys(text);
    }

    /**
     * Envia el contenido de text al WebElement.
     *
     * @param webElement - Tipo de entrada WebElement.
     * @param text       - Contenido para el WebElement.
     */
    public void setInputFieldDocument(WebElement webElement, String text) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value='';", webElement);
        webElement.sendKeys(text);
    }

    /**
     * Envia el contenido de text al By selector.
     *
     * @param selector - Tipo de entrada By selector.
     * @param text     - Contenido para el By selector.
     */
    public void setInputField(By selector, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        WebElement webElement = driver.findElement(selector);
        clearTextField(webElement);
        webElement.sendKeys(text);
    }

    /**
     * Ingrese texto y presiona Enter.
     *
     * @param element - elemento del campo al que se introduce el texto.
     * @param text    - testo a introducir.
     */
    public void setInputFieldAndPressEnter(WebElement element, String text) {
        setInputField(element, text);
        element.sendKeys(Keys.ENTER);
    }

    /**
     * Realiza el click en el webElement
     *
     * @param webElement WebElement al que se hara el click.
     */
    public void clickElement(WebElement webElement) {
        int contador = 0;
        while (contador < 5) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(webElement));
                webElement.click();
                contador = 5;
            } catch (ElementClickInterceptedException | StaleElementReferenceException | TimeoutException e) {
                log.error(e.getMessage());
                contador++;
            }
        }

    }

    /**
     * Realiza el click en el webElement
     *
     * @param element WebElement al que se hara el click.
     */
    public void clickElementLast(By element) {
        List<WebElement> elements = driver.findElements(element);
        int size = elements.size();
        WebElement webElement = elements.get(size - 1);
        int contador = 0;
        while (contador < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(webElement));
                webElement.click();
                contador = 3;
            } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
                log.error(e.getMessage());
                contador++;
            }
        }
    }

    /**
     * Realiza el click en el By element
     *
     * @param by By al que se hara el click.
     */
    public void clickElement(By by) {
        int contador = 0;
        while (contador < 5) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(by));
                WebElement webElement = driver.findElement(by);
                webElement.click();
                break;
            } catch (ElementClickInterceptedException | StaleElementReferenceException e) {
                log.warn(e.getMessage());
                contador++;
            }
        }
    }

    /**
     * Devuelve el contenido del WebElement.
     *
     * @param webElement - WebElement al que se le pide el contenido.
     * @return Retorna el texto del WebElement.
     */
    public String getElementText(WebElement webElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.getText();
        } catch (StaleElementReferenceException e) {
            log.warn(e.getMessage());
            return "";
        }
    }

    /**
     * Devuelve el contenido del WebElement.
     *
     * @param by - By al que se le pide el contenido.
     * @return Retorna el texto del WebElement.
     */
    public List<String> getElementTexts(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        List<WebElement> listTexts = driver.findElements(by);
        List<String> result = new ArrayList<>();
        for (WebElement text : listTexts) {
            result.add(text.getText());
        }
        return result;
    }

    /**
     * Devuelve el contenido del WebElement.
     *
     * @param by - By al que se le pide el contenido.
     * @return Retorna el texto del WebElement.
     */
    public String getElementTextLast(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        List<WebElement> listTexts = driver.findElements(by);
        return listTexts.get(listTexts.size() - 1).getText();
    }

    /**
     * Devuelve el contenido del WebElement.
     *
     * @param webElement - WebElement al que se le pide el contenido.
     * @return Retorna el valor del WebElement.
     */
    public String getElementValue(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getAttribute("value");
    }

    /**
     * Devuelve el contenido del WebElement.
     *
     * @param by - by al que se le pide el contenido.
     * @return Retorna el valor del WebElement.
     */
    public String getElementValue(By by) {
        WebElement webElement = driver.findElement(by);
        return getElementValue(webElement);
    }

    /**
     * Devuelve el contenido del By element.
     *
     * @param by - By element al que se le pide el contenido.
     * @return Retorna el texto del By element.
     */
    public String getElementText(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement e = driver.findElement(by);
        return e.getText();
    }

    /**
     * Este metodo selecciona un item de un campo autocomplete
     *
     * @param webElement webelement
     * @param text       texto base
     * @param textSelect opciona seleccionar
     */
    public void selectAutoComplete(WebElement webElement, String text, String textSelect) {
        int counter = 0;
        while (counter < 5) {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            clearTextField(webElement);
            webElement.sendKeys(text);
            WebElement autoOptions = driver.findElement(By.cssSelector("div[class^='select-options']"));

            List<WebElement> optionsToSelect = autoOptions.findElements(By.cssSelector("div[class^='option']"));
            for (WebElement option : optionsToSelect) {
                if (option.getText().equals(textSelect)) {
                    clickElement(option);
                    sleepSeconds(3);
                    break;
                }
            }
            if (getElementValue(webElement).equalsIgnoreCase(textSelect)) {
                log.info(String.format("dato seleccionado %s", getElementValue(webElement)));
                break;
            } else counter++;
        }
    }

    /**
     * Selecciona un check box
     *
     * @param webElement webElement
     */
    public void selectCheckbox(WebElement webElement) {
        if (!isElementSelected(webElement)) {
            try {
                clickElement(webElement);
            } catch (WebDriverException e) {
                moveAndClickElement(webElement);
            }

        }
    }

    public void clickCheckboxElement(WebElement webElement) {
        if (!webElement.isSelected()) {
            webElement.click();
        }
    }

    /**
     * Limpia los chkbox
     *
     * @param webElement webElement
     */
    public void selectChkBoxAttribute(WebElement webElement) {
        if (!isElementSelectedAttribute(webElement)) {
            try {
                clickElement(webElement);
            } catch (WebDriverException e) {
                moveAndClickElement(webElement);
            }
        }
    }

    /**
     * Selecciona un check box
     *
     * @param by by
     */
    public void selectCheckbox(By by) {
        WebElement webElement = driver.findElement(by);
        selectCheckbox(webElement);
    }

    /**
     * Seleciona todos los chkbox
     *
     * @param webElement webElement
     */
    public void selectAllChkBox(WebElement webElement) {
        List<WebElement> webElements = webElement.findElements(CHK_BOX);
        for (WebElement element : webElements) {
            selectCheckbox(element);
        }
    }

    /**
     * Seleciona todos los chkbox
     *
     * @param by by
     */
    public void selectAllChkBox(By by) {
        WebElement webElement = driver.findElement(by);
        selectAllChkBox(webElement);
    }

    /**
     * Limpia los chkbox
     *
     * @param webElement webElement
     */
    public void clearChkBox(WebElement webElement) {
        if (isElementSelected(webElement)) {
            try {
                clickElement(webElement);
            } catch (WebDriverException e) {
                moveAndClickElement(webElement);
            }
        }
    }

    /**
     * Limpia los chkbox
     *
     * @param webElement webElement
     */
    public void clearChkBoxAttribute(WebElement webElement) {
        if (isElementSelectedAttribute(webElement)) {
            try {
                clickElement(webElement);
            } catch (WebDriverException e) {
                moveAndClickElement(webElement);
            }
        }
    }

    /**
     * Limpia la seleccion del switch button
     *
     * @param webElement    webElement
     * @param elementStatus webElement
     */
    public void clearSwitchAttribute(WebElement webElement, WebElement elementStatus) {
        if (isElementSelectedAttribute(elementStatus)) {
            try {
                clickElement(webElement);
            } catch (WebDriverException e) {
                moveAndClickElement(webElement);
            }
        }
    }

    /**
     * Limpia los chkbox
     *
     * @param by by locator
     */
    public void clearChkBox(By by) {
        WebElement webElement = driver.findElement(by);
        clearChkBox(webElement);
    }

    /**
     * Limpia todos los chkbox
     *
     * @param webElement webElement
     */
    public void clearAllChkBox(WebElement webElement) {
        List<WebElement> webElements = webElement.findElements(CHK_BOX);
        for (WebElement element : webElements) {
            clearChkBox(element);
        }
    }

    /**
     * Limpia todos los chkbox
     *
     * @param by by
     */
    public void clearAllChkBox(By by) {
        WebElement webElement = driver.findElement(by);
        clearAllChkBox(webElement);
    }


    /**
     * Se mueve el elemento y se realiza un click.
     *
     * @param webElement webElement
     */
    public void moveAndClickElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    /**
     * Se mueve el elemento y se realiza un click.
     *
     * @param by by
     */
    public void moveAndClickElement(By by) {
        WebElement webElement = driver.findElement(by);
        moveAndClickElement(webElement);
    }

    /**
     * Se mueve hacia el elemento seleccionado.
     */
    public void moveToElement(By by) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Se mueve hacia el elemento seleccionado.
     */
    public void moveToElementMouse(By by) {
        WebElement webElement = wait.until(webDriver -> webDriver.findElement(by));
        Actions action = new Actions(driver);
        action.moveToElement(webElement).build().perform();
    }

    /**
     * Se mueve hacia el elemento seleccionado.
     */
    public void moveToElementMouse(WebElement webElement) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(webElement).build().perform();
        } catch (MoveTargetOutOfBoundsException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Se mueve hacia el elemento seleccionado.
     *
     * @param webElement webElement
     */
    public void moveToElement(WebElement webElement) {
        Actions action = new Actions(driver);
        action.moveToElement(webElement);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }


    /**
     * Espera hasta que aparesca el elemento buscado.
     *
     * @param webElement elemento a buscar.
     * @return true si lo encuentra sino false.
     */
    public boolean isElementDisplayed(WebElement webElement) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(WebDriverConfigReader.getInstance().getWaitSleepTime())).
                ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            log.warn(e.getMessage());
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfigReader.getInstance().
                    getImplicitWaitTime()));
            wait = new FluentWait<>(driver).
                    withTimeout(Duration.ofSeconds(WebDriverConfigReader.getInstance().getExplicitWaitTime())).
                    pollingEvery(Duration.ofMillis(WebDriverConfigReader.getInstance().getWaitSleepTime())).
                    ignoring(NoSuchElementException.class);
        }
    }

    /**
     * Espera hasta que aparesca el elemento buscado.
     *
     * @param by elemento a buscar.
     * @return true si lo encuentra sino false.
     */
    public boolean isElementDisplayed(By by) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(5)).
                pollingEvery(Duration.ofMillis(500)).
                ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            log.warn(e.getMessage());
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfigReader.getInstance().
                    getImplicitWaitTime()));
            wait = new FluentWait<>(driver).
                    withTimeout(Duration.ofSeconds(WebDriverConfigReader.getInstance().getExplicitWaitTime())).
                    pollingEvery(Duration.ofMillis(WebDriverConfigReader.getInstance().getWaitSleepTime())).
                    ignoring(NoSuchElementException.class);
        }
    }

    /**
     * Espera hasta que aparesca el elemento buscado.
     *
     * @param by           - elemento a buscar.
     * @param intervalTime - Tiempo en seconds a esperar.
     * @return true si lo encuentra sino false.
     */
    public boolean isElementDisplayed(By by, int intervalTime) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(intervalTime));
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            log.warn(e.getMessage());
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfigReader.getInstance().
                    getImplicitWaitTime()));
        }
    }

    /**
     * Verifica si el elemento checkbox esta seleccionado.
     *
     * @param element WebElement.
     * @return true si esta seleccionado sino falso.
     */
    public boolean isElementSelectedAttribute(WebElement element) {
        String chkboxStatus = element.getAttribute("class");
        return chkboxStatus.equals("active");
    }

    /**
     * Verifica si el elemento checkbox esta seleccionado.
     *
     * @param element WebElement.
     * @return true si esta seleccionado sino falso.
     */
    public boolean isElementSelected(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.isSelected();
    }

    /**
     * Espera hasta que elemento esperado se muestre.
     *
     * @param element - elemento a esperar.
     */
    public void waitUntilElementDisplayed(WebElement element) {
        int contador = 0;
        while (contador < 6) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                contador = 6;
            } catch (TimeoutException e) {
                log.warn(e.getMessage());
                contador++;
            }
        }
    }

    /**
     * Espera hasta que elemento esperado se muestre.
     *
     * @param element - elemento a esperar.
     * @param tries   - numero de intentos a esperar, un intento equivale a 1 minuto
     */
    public void waitUntilElementDisplayed(WebElement element, int tries) {
        int contador = 0;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(3)).
                pollingEvery(Duration.ofMillis(WebDriverConfigReader.getInstance().getWaitSleepTime())).
                ignoring(NoSuchElementException.class);
        while (contador < tries * 20) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                contador = tries * 20;
            } catch (TimeoutException e) {
                log.warn(e.getMessage());
                contador++;
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfigReader.getInstance().
                getImplicitWaitTime()));
        wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(WebDriverConfigReader.getInstance().getExplicitWaitTime())).
                pollingEvery(Duration.ofMillis(WebDriverConfigReader.getInstance().getWaitSleepTime())).
                ignoring(NoSuchElementException.class);
    }

    /**
     * Espera hasta que elemento esperado se muestre.
     *
     * @param by - elemento a esperar.
     */
    public void waitUntilElementDisplayed(By by) {
        int contador = 0;
        while (contador < 6) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                contador = 6;
            } catch (TimeoutException e) {
                log.warn(e.getMessage());
                contador++;
            }
        }
    }

    /**
     * Espera hasta que elemento esperado se desaparece.
     *
     * @param element - elemento a esperar.
     */
    public void waitUntilElementDisappear(WebElement element) {
        int contador = 0;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(1)).
                pollingEvery(Duration.ofMillis(WebDriverConfigReader.getInstance().getWaitSleepTime())).
                ignoring(NoSuchElementException.class);
        while (contador < 120) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                sleepSeconds(1);
                contador++;
            } catch (TimeoutException | StaleElementReferenceException e) {
                log.warn(e.getMessage());
                break;
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfigReader.getInstance().
                getImplicitWaitTime()));
        wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(WebDriverConfigReader.getInstance().getExplicitWaitTime())).
                pollingEvery(Duration.ofMillis(WebDriverConfigReader.getInstance().getWaitSleepTime())).
                ignoring(NoSuchElementException.class);
    }

    /**
     * Selecciona una opcion seleccionada.
     *
     * @param selector - elemento a buscar
     * @param option   - valor a seleccionar
     */
    public void selectOptionListBox(By selector, String option) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        new Select(driver.findElement(selector)).selectByVisibleText(option);
    }

    /**
     * Selecciona un item de la lista de box.
     *
     * @param element - ListBox WebElement.
     * @param value   - Valor a seleccionar del ListBox.
     */
    public void selectListBoxByValue(WebElement element, String value) {
        int count = 0;
        while (count < 5) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                Select select = new Select(element);
                select.selectByValue(value);
                count = 5;
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                log.warn(e.getSystemInformation());
                moveToElement(element);
                count++;
            }
        }
    }

    /**
     * Selecciona un item de la lista de box.
     *
     * @param by    - ListBox WebElement.
     * @param value - Valor a seleccionar del ListBox.
     */
    public void selectListBoxByValue(By by, String value) {
        int count = 0;
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        wait = new FluentWait<>(driver).
                withTimeout(Duration.ofMillis(500)).
                pollingEvery(Duration.ofMillis(1)).
                ignoring(NoSuchElementException.class);
        while (count < 100) {
            try {
                Select select = new Select(wait.until(webDriver -> webDriver.findElement(by)));
                select.selectByValue(value);
                break;
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                log.warn(e.getMessage());
                clickElement(by);
                count++;
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WebDriverConfigReader.getInstance().
                getImplicitWaitTime()));
        wait = new FluentWait<>(driver).
                withTimeout(Duration.ofSeconds(WebDriverConfigReader.getInstance().getExplicitWaitTime())).
                pollingEvery(Duration.ofMillis(WebDriverConfigReader.getInstance().getWaitSleepTime())).
                ignoring(NoSuchElementException.class);
    }

    /**
     * Selecciona un item de la lista de box.
     *
     * @param element - ListBox WebElement.
     * @param value   - Valor a seleccionar del ListBox.
     */
    public void selectListBoxByVisibleText(WebElement element, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).selectByVisibleText(value);
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.visibilityOf(element));
            new Select(element).selectByVisibleText(value);
        }
    }

    /**
     * Verifica si un campo esta habilitado
     *
     * @param webElement elemento a verificar
     * @return true si esta habilitado sino false
     */
    public boolean isEnable(WebElement webElement) {
        return webElement.isEnabled();
    }

    /**
     * Cierra las alertas
     */
    public void closeAlert() {
        int count = 0;
        while (count < 5) {
            try {
                driver.switchTo().alert().accept();
                count = 5;
            } catch (NoAlertPresentException | UnhandledAlertException e) {
                log.warn(e.getMessage());
                count++;
                sleepSeconds(1);
            }
        }
    }
}
