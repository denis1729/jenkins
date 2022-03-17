package ui.category;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.PageFactory;

public class DressesPage extends WomanPage {
    @FindBy(xpath = "//*[@id='subcategories']//child::a[text()='Dresses']")
    private WebElement dressesBtn;

    private void clickTopsButton() {
        driverTools.clickElement(dressesBtn);
    }

    @Override
    public void goToPage() {
        clickTopsButton();
    }

    @Override
    public WomanPage goToSubcategoryPage(String subcategory) {
        return PageFactory.getDresses(subcategory);
    }
}
