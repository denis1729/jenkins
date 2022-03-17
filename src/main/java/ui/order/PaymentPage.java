package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class PaymentPage extends Cart {
    @FindBy(xpath = "//*[@id='cart_navigation']//child::span")
    private WebElement confirmBtn;


    public void clickConfirmButton() {
        driverTools.clickElement(confirmBtn);
    }

    public abstract void selectMethodPay();
    public abstract String getBuyMessage();

}
