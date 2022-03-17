package ui.category.subcategory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.category.TopsPage;

public class TShirts extends TopsPage {
    @FindBy(xpath = "//*[@id='subcategories']//child::a[text()='T-shirts']")
    private WebElement tShirtsBtn;


    private void clickTShirtsButton() {
        driverTools.clickElement(tShirtsBtn);
    }

    @Override
    public void goToPage() {
        clickTShirtsButton();
    }

}
