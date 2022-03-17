package ui.category;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.PageFactory;

public class TopsPage extends WomanPage {
    @FindBy(xpath = "//*[@id='subcategories']//child::a[text()='Tops']")
    private WebElement topsBtn;

    private void clickTopsButton() {
        driverTools.clickElement(topsBtn);
    }

    @Override
    public void goToPage() {
        clickTopsButton();
    }

    @Override
    public WomanPage goToSubcategoryPage(String subcategory) {
        return PageFactory.getTops(subcategory);
    }
}
