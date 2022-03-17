package ui.order;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PayBankCheck extends PaymentPage {
    @FindBy(xpath = "//*[@id='HOOK_PAYMENT']//following::a[@class='bankwire']")
    private WebElement payMethodBtn;

    @FindBy(xpath = "//*[@id='center_column']/div/p/strong")
    private WebElement message;

    private void clickPayMethodButton() {
        driverTools.clickElement(payMethodBtn);
    }
    @Override
    public String getBuyMessage() {
        return driverTools.getElementText(message);
    }

    @Override
    public void selectMethodPay() {
        clickPayMethodButton();
    }
}
