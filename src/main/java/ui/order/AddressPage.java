package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressPage extends Cart {
    @FindBy(xpath = "//*[@id='center_column']//following::span[text()='Proceed to checkout']")
    private WebElement proceedCheckoutBtn;

    private ShippingPage clickProceedCheckoutButton() {
        driverTools.clickElement(proceedCheckoutBtn);
        return new ShippingPage();
    }

    public ShippingPage proceedCheckoutShipping() {
        return clickProceedCheckoutButton();
    }
}
