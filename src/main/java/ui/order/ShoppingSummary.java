package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingSummary extends Cart {

    @FindBy(xpath = "//*[@id='center_column']//following::span[text()='Proceed to checkout']")
    private WebElement proceedCheckoutBtn;

    private AddressPage clickProceedCheckoutButton() {
        driverTools.clickElement(proceedCheckoutBtn);
        return new AddressPage();
    }

    public AddressPage proceedCheckoutAddress() {
        return clickProceedCheckoutButton();
    }

}
