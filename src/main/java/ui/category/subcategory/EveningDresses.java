package ui.category.subcategory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;
import ui.PageFactory;
import ui.category.DressesPage;
import ui.category.WomanPage;

public class EveningDresses extends DressesPage {
    @FindBy(xpath = "//*[@id='subcategories']//child::a[text()='Evening Dresses']")
    private WebElement dressesBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager
     */
    public EveningDresses(WebDriverManager webDriverManager) {
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
