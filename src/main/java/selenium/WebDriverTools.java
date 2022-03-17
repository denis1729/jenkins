package selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Class to manage web driver tools.
 *
 * @author Silvia Valencia
 * @since 2/2/2018
 */
public class WebDriverTools {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger log;

    /**
     * Generic checkbox to select or clear.
     */
    private static final By GENERIC_CHKBOX_BY = By.cssSelector("input[type='checkbox']");

    /**
     * Constructor.
     */
    public WebDriverTools() {
        log = Logger.getLogger(getClass());
        this.driver = WebDriverManager.getInstance().getWebDriver();
        this.wait = WebDriverManager.getInstance().getWait();
    }

    /**
     * Waits and clear the WebElement.
     *
     * @param webElement WebElement to wait and clear.
     */
    public void clearTextField(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
    }

    /**
     * Sets an Input Field.
     *
     * @param webElement - Input WebElement.
     * @param text       - Text to fill.
     */
    public void setInputField(WebElement webElement, String text) {
//        wait.until(ExpectedConditions.visibilityOf(webElement));
        clearTextField(webElement);
        webElement.sendKeys(text);
    }

    /**
     * Sets an input field using selector.
     *
     * @param selector - Selector value to find input element.
     * @param text     - Value to fill in input.
     */
    public void setInputField(By selector, String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        WebElement webElement = driver.findElement(selector);
        clearTextField(webElement);
        webElement.sendKeys(text);
    }

    /**
     * Cleans the text box and set new value using javascript.
     *
     * @param element - element to set text.
     * @param text    - Value to fill in input.
     */
    public void setInputFieldJavaScript(WebElement element, String text) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = ''", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", element, text);
    }

    /**
     * Sets text into a Kendo Editor textarea using JQuery.
     *
     * @param id   - Textarea id value.
     * @param text - Value to fill in textarea.
     */
    public void setKendoEditorField(String id, String text) {
        ((JavascriptExecutor) driver).executeScript("jQuery('" + id + "').data('kendoEditor').value('" + text + "')");
    }

    /**
     * Waits and click on the webElement.
     *
     * @param webElement WebElement to wait and click.
     */
    public void clickElement(WebElement webElement) {
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        webElement.click();
    }

    public void clickCheckboxElement(WebElement webElement) {
        if (!webElement.isSelected()) {
            webElement.click();
        }
    }

    /**
     * Waits and click on the webElement.
     *
     * @param by By to wait and click.
     */
    public void clickElement(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    /**
     * Waits and gets the text of a WebElement.
     *
     * @param webElement - WebElement to wait and get the text.
     * @return Text of the WebElement.
     */
    public String getElementText(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    /**
     * Moves to element and click using javascript.
     *
     * @param by - Locator of element.
     */
    public void moveAndClickElement(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = driver.findElement(by);
        moveAndClickElement(element);
    }

    /**
     * Moves to element and click using javascript.
     *
     * @param webElement - WebElement to wait and click.
     */
    public void moveAndClickElement(WebElement webElement) {
        wait.until(ExpectedConditions.visibilityOf(webElement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElement);
    }

    /**
     * Verifies if the WebElement is selected.
     *
     * @param element WebElement.
     * @return true if the WebElement is selected, false otherwise.
     */
    public boolean isElementSelected(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element.isSelected();
    }

    /**
     * Waits until a web element is displayed.
     *
     * @param webElement - The WebElement to search.
     * @return True if the WebElement is displayed, false otherwise.
     */
    public boolean isElementDisplayed(WebElement webElement) {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            wait.until(ExpectedConditions.visibilityOf(webElement));
            return webElement.isDisplayed();
        } catch (Exception e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(WebDriverConfigReader
                    .getInstance().getImplicitWaitTime(), TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Verifies if an element is displayed.
     * Finds the element and return true/false if it is displayed.
     *
     * @param by - The element to find.
     * @return True if the WebElement is displayed, false otherwise.
     */
    public boolean isElementDisplayed(By by) {
        return isElementDisplayed(by, 3000);
    }

    /**
     * Verifies if an element is displayed.
     * Uses an interval time to wait and find the element and return true if it is displayed.
     *
     * @param by           - Selector of element to Find
     * @param intervalTime - Time in miliseconds to wait.
     * @return True if the element is found and displayed, false otherwise.
     */
    public boolean isElementDisplayed(By by, int intervalTime) {
        driver.manage().timeouts().implicitlyWait(intervalTime, TimeUnit.MILLISECONDS);
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            driver.manage().timeouts().implicitlyWait(WebDriverConfigReader
                    .getInstance().getImplicitWaitTime(), TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Waits for an element is displayed.
     *
     * @param byElement - Element to wait.
     * @return True if the element is found, false otherwise.
     */
    public boolean waitUntilElementDisplayed(By byElement) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    /**
     * Selects the CheckBox.
     *
     * @param element WebElement.
     */
    public void selectChkBox(WebElement element) {
        if (!isElementSelected(element)) {
            try {
                clickElement(element);
            } catch (WebDriverException e) {
                moveAndClickElement(element);
            }
        }
    }

    /**
     * Selects a check Box.
     *
     * @param by - Check Box element locator to select.
     */
    public void selectChkBox(By by) {
        WebElement element = driver.findElement(by);
        selectChkBox(element);
    }

    /**
     * Clears the ListBox.
     *
     * @param element WebElement.
     */
    public void clearChkBox(WebElement element) {
        if (isElementSelected(element)) {
            try {
                clickElement(element);
            } catch (WebDriverException e) {
                moveAndClickElement(element);
            }
        }
    }

    /**
     * Selects an option by text from a select element.
     *
     * @param selector - Locator to find the select element
     * @param option   - Value to select
     */
    public void selectOptionListBox(By selector, String option) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        new Select(driver.findElement(selector)).selectByVisibleText(option);
    }

    /**
     * Selects an item from ListBox.
     *
     * @param element   - ListBox WebElement.
     * @param valueText - Text value to select in a ListBox.
     */
    public void selectListBoxByVisibleValue(WebElement element, String valueText) {
        wait.until(ExpectedConditions.visibilityOf(element));
        new Select(element).selectByVisibleText(valueText);
    }

    /**
     * Selects an item from ListBox by its value.
     *
     * @param element - ListBox WebElement.
     * @param value   - Value to select in a ListBox.
     */
    public void selectListBoxByValue(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        new Select(element).selectByValue(value);
    }

    /**
     * Gets an attribute value from an element.
     *
     * @param by        - Element selector.
     * @param attribute - Attribute name to get value.
     * @return Value from attribute.
     */
    public String getAttributeValueFromElement(By by, String attribute) {
        String attributeValue = "";
        try {
            attributeValue = driver.findElement(by).getAttribute(attribute);
            return attributeValue;
        } catch (Exception e) {
            return attributeValue;
        }
    }

    /**
     * Refreshes the current page.
     */
    public void refreshPage() {
        driver.navigate().refresh();
    }

    /**
     * Allows a test to be paused for the given time.
     *
     * @param millSeconds - he length of time to sleep in milliseconds.
     */
    public void sleepMilliSeconds(int millSeconds) {
        try {
            Thread.sleep(millSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refresh the page until an element is displayed.
     *
     * @param by             - Selector of the element to wait.
     * @param attemptsNumber - Number of retries.
     * @param intervalTime   - Interval time to wait in Milliseconds.
     * @return True if the element is found, else otherwise.
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
     * Clicks on a button, then wait for an element is displayed.
     * Repeat this for several times according attemptsNumber parameter.
     *
     * @param by             - Selector of element to wait is displayed.
     * @param buttonSelector - Button selector to click.
     * @param attemptsNumber - Number of times to click on button.
     * @param intervalTime   - Time in miliseconds to wait for the element.
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
     * Clicks on a button, then wait for an element is not displayed.
     * Repeat this for several times according the attemptsNumber parameter.
     *
     * @param by             - Selector of element to wait is not displayed.
     * @param buttonSelector - Button selector to click.
     * @param attemptsNumber - Number of times to click on button.
     * @param intervalTime   - Time in miliseconds to wait for the element.
     * @return True if element is not displayed, false otherwise.
     */
    public boolean clickOnButtonUntilElementIsNotDisplayed(By by, By buttonSelector, int attemptsNumber,
                                                           int intervalTime) {
        int attempt = 1;
        boolean elementDisplayed = true;

        while (elementDisplayed && attempt <= attemptsNumber) {
            elementDisplayed = isElementDisplayed(by);
            if (elementDisplayed) {
                sleepMilliSeconds(intervalTime);
                clickElement(buttonSelector);
            }
            attempt++;
        }

        // Need to deny result because if the element is not displayed it will return true;
        return !elementDisplayed;
    }

    /**
     * Pastes text from the clipboard to a given locator.
     *
     * @param element - WebElement to set.
     * @param value   - String to copy.
     */
    public void pasteToLocator(WebElement element, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1]", element, value);
    }

    /**
     * Scrolls to the bottom of the page.
     */
    public void scrollToBottomOfPage() {
        ((JavascriptExecutor) driver).executeScript("scroll(0, 2500);");
    }

    /**
     * Scrolls down by hitting down arrow x number of times.
     *
     * @param numberOfDowns - Number of times to scroll down
     */
    public void scrollDown(int numberOfDowns) {
        Actions action = new Actions(driver);
        for (int i = 0; i < numberOfDowns; i++) {
            action.sendKeys(Keys.ARROW_DOWN).perform();
        }
    }

    /**
     * Clears all check boxes.
     *
     * @param element - Element to search a generic check box.
     */
    public void clearAllCheckboxes(WebElement element) {
        List<WebElement> allCheckBoxes = element.findElements(GENERIC_CHKBOX_BY);
        for (WebElement elementSelect : allCheckBoxes) {
            clearChkBox(elementSelect);
        }
    }

    /**
     * Selects all check boxes.
     *
     * @param element - Element to search a generic check box.
     */
    public void selectAllCheckboxes(WebElement element) {
        List<WebElement> allCheckBoxes = element.findElements(GENERIC_CHKBOX_BY);
        for (WebElement elementSelect : allCheckBoxes) {
            selectChkBox(elementSelect);
        }
    }

    /**
     * Clicks on the first element displayed.
     *
     * @param by - Locator to find the click element.
     */
    public void clickOnElementDisplayed(By by) {
        List<WebElement> element = driver.findElements(by);
        for (WebElement sb : element) {
            if (sb.isDisplayed()) {
                Actions action = new Actions(driver);
                action.moveToElement(sb, 5, 5).click().perform();
                break;
            }
        }
    }

    /**
     * Expands a section.
     *
     * @param sectionLocator - Section locator to expands.
     */
    public void expandSection(By sectionLocator) {
        if (!driver.findElement(sectionLocator).getAttribute("data-collapse-target-state").contains("not-collapsed")) {
            clickElement(sectionLocator);
        }
    }

    /**
     * Sets text into a Kendo Editor textarea using JQuery.
     *
     * @param id     - Textarea id value.
     * @param number - Value to fill in textarea.
     */
    public void setKendoNumericBox(String id, int number) {
        ((JavascriptExecutor) driver).executeScript("jQuery('" + id
                + "').kendoNumericTextBox({value:" + number
                + ", decimals: false, format: 'n0'})");
    }

    /**
     * Sets text in input field and press Enter.
     *
     * @param element - The web element.
     * @param text    - The text value.
     */
    public void setInputFieldAndPressEnter(WebElement element, String text) {
        setInputField(element, text);
        element.sendKeys(Keys.ENTER);
    }

    /**
     * Sets text in input field and press Enter.
     *
     * @param selector - Selector value to find input element
     * @param text     - Value to fill in input
     */
    public void setInputFieldAndPressEnter(By selector, String text) {
        setInputField(selector, text);
        WebElement element = driver.findElement(selector);
        element.sendKeys(Keys.ENTER);
    }

    /**
     * Clears the ListBox.
     *
     * @param productChkActive web element.
     * @param active           check or uncheck.
     */
    public void selectChkBox(WebElement productChkActive, boolean active) {
        if (active) {
            selectChkBox(productChkActive);
        } else {
            clearChkBox(productChkActive);
        }
    }

    /**
     * wait for the invisibility.
     *
     * @param webElement web element.
     */
    public void invisibilityElement(WebElement webElement) {
        if (isElementDisplayed(webElement))
            wait.until(ExpectedConditions.invisibilityOf(webElement));
        else
            wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

}
