package ui.category;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;
import ui.PageFactory;

public class TopsPage extends WomanPage {
    @FindBy(xpath = "//*[@id='subcategories']//child::a[text()='Tops']")
    private WebElement topsBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager
     */
    public TopsPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    private void clickTopsButton() {
        driverTools.clickElement(topsBtn);
    }

    @Override
    public void goToPage() {
        clickTopsButton();
    }

    @Override
    public WomanPage goToSubcategoryPage(String subcategory) {
        return PageFactory.getTops(subcategory,this.webDriverManager);
    }
}
