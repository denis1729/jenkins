package ui.popup;

import org.openqa.selenium.By;
import selenium.WebDriverManager;
import ui.order.ShoppingSummary;

public class CartPopup extends PopupPage {

    private final By proceedCheckoutBtn = By.cssSelector("[title='Proceed to checkout']");
    private final By continueShoppingBtn = By.cssSelector("[title='Continue shopping']");

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager driver
     */
    public CartPopup(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public ShoppingSummary proceedCheckoutShopping() {
        driverTools.clickElement(proceedCheckoutBtn);
        return new ShoppingSummary(this.webDriverManager);
    }

}
