package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShippingPage extends Cart {
    @FindBy(xpath = "//*[@id='form']/p/button/span")
    private WebElement proceedCheckoutBtn;

    @FindBy(xpath = "//*[@id='cgv']")
    private WebElement agreeCheckbox;


    private void clickCheckboxButton() {
        driverTools.clickCheckboxElement(agreeCheckbox);
    }

    private void clickProceedCheckoutButton() {
        driverTools.clickElement(proceedCheckoutBtn);
    }

    public void proceedCheckoutShipping() {
        clickProceedCheckoutButton();
    }

    public void agreeCheckbox() {
        clickCheckboxButton();
    }
}
