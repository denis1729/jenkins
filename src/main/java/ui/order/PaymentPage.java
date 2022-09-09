package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;

public abstract class PaymentPage extends Cart {
    @FindBy(xpath = "//*[@id='cart_navigation']//child::span")
    private WebElement confirmBtn;

    @FindBy(css = ".box>.price>strong")
    private WebElement amount;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager web
     */
    protected PaymentPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }


    public void clickConfirmButton() {
        driverTools.clickElement(confirmBtn);
    }

    public abstract void selectMethodPay();

    public abstract String getBuyMessage();

    /**
     * This method return the total price of the sell
     *
     * @return the total price of the sell
     */
    public String getAmount() {
        return driverTools.getElementText(amount);
    }
}
