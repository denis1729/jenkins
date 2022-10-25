package ui.category.subcategory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;
import ui.PageFactory;
import ui.category.DressesPage;
import ui.category.WomanPage;

public class CasualDresses extends DressesPage {
    @FindBy(xpath = "//*[@id='subcategories']//child::a[text()='Casual Dresses']")
    private WebElement dressesBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager driver
     */
    public CasualDresses(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    private void clickTopsButton() {
        driverTools.clickElement(dressesBtn);
    }

    @Override
    public void goToPage() {
        clickTopsButton();
    }

    @Override
    public WomanPage goToSubcategoryPage(String subcategory) {
        return PageFactory.getDresses(subcategory,this.webDriverManager);
    }
}
