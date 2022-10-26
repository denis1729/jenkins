package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;

public abstract class PaymentPage extends Cart {
    @FindBy(xpath = "//*[@id='cart_navigation']//child::span")
    private WebElement confirmBtn;

    @FindBy(css = "span.price>strong")
    private WebElement amountLabel;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager driver
     */
    protected PaymentPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }


    public void clickConfirmButton() {
        driverTools.clickElement(confirmBtn);
    }

    public abstract void selectMethodPay();

    public abstract String getBuyMessage();

    public String getAmount() {
        return driverTools.getElementText(amountLabel).trim();
    }

}
