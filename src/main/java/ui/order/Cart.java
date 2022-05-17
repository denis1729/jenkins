package ui.order;

import org.openqa.selenium.By;

import selenium.WebDriverManager;
import ui.HomePage;

public class Cart extends HomePage {

    private final By cartBtn = By.cssSelector("#add_to_cart>button");
    private final By proceedCheckoutBtn = By.cssSelector("[title='Proceed to checkout']");

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager
     */
    public Cart(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    private void clickCartButton() {
        driverTools.clickElement(cartBtn);
    }

    private ShoppingSummary clickProceedCheckoutButton() {
        driverTools.clickElement(proceedCheckoutBtn);
        return new ShoppingSummary(this.webDriverManager);
    }

    public void addItemToCart() {
        clickCartButton();
    }

    public ShoppingSummary proceedCheckoutShopping() {
        return clickProceedCheckoutButton();
    }

}
