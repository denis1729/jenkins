package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;

public class ShoppingSummary extends Cart {

    @FindBy(xpath = "//*[@id='center_column']//following::span[text()='Proceed to checkout']")
    private WebElement proceedCheckoutBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager web
     */
    public ShoppingSummary(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public AddressPage proceedCheckoutAddress() {
        driverTools.clickElement(proceedCheckoutBtn);
        return new AddressPage(this.webDriverManager);
    }

}
