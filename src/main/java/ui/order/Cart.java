package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.HomePage;

public class Cart extends HomePage {
    @FindBy(id = "add_to_cart")
    private WebElement cartBtn;
    @FindBy(xpath = "//*[contains(@title,'Proceed')]")
    private WebElement proceedCheckoutBtn;

    private void clickCartButton() {
        driverTools.clickElement(cartBtn);
    }

    private ShoppingSummary clickProceedCheckoutButton() {
        driverTools.clickElement(proceedCheckoutBtn);
        return new ShoppingSummary();
    }

    public void addItemToCart() {
        clickCartButton();
    }

    public ShoppingSummary proceedCheckoutShopping() {
        return clickProceedCheckoutButton();
    }

}
