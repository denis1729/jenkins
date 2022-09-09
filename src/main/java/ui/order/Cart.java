package ui.order;

import org.openqa.selenium.By;

import selenium.WebDriverManager;
import ui.HomePage;

public class Cart extends HomePage {

    private final By cartBtn = By.cssSelector("#add_to_cart>button");
    private final By proceedCheckoutBtn = By.cssSelector("[title='Proceed to checkout']");
    private final By quantityInput = By.id("quantity_wanted");

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager web
     */
    public Cart(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    private ShoppingSummary clickProceedCheckoutButton() {
        driverTools.clickElement(proceedCheckoutBtn);
        return new ShoppingSummary(this.webDriverManager);
    }

    public void addItemToCart() {
        driverTools.clickElement(cartBtn);
    }

    public void addItemToCart(int quantity) {
        driverTools.setInputField(quantityInput, String.valueOf(quantity));
        driverTools.clickElement(cartBtn);
    }

    public ShoppingSummary proceedCheckoutShopping() {
        return clickProceedCheckoutButton();
    }

}
