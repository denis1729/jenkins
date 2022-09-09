package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;

public class ShippingPage extends Cart {
    @FindBy(xpath = "//*[@id='form']/p/button/span")
    private WebElement proceedCheckoutBtn;

    @FindBy(xpath = "//*[@id='cgv']")
    private WebElement agreeCheckbox;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager web
     */
    public ShippingPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public void proceedCheckoutShipping() {
        driverTools.clickElement(proceedCheckoutBtn);
    }

    public void agreeCheckbox() {
        driverTools.clickCheckboxElement(agreeCheckbox);
    }
}
