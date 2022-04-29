package ui.category.subcategory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;
import ui.category.TopsPage;

public class TShirts extends TopsPage {
    @FindBy(xpath = "//*[@id='subcategories']//child::a[text()='T-shirts']")
    private WebElement tShirtsBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager
     */
    public TShirts(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }


    private void clickTShirtsButton() {
        driverTools.clickElement(tShirtsBtn);
    }

    @Override
    public void goToPage() {
        clickTShirtsButton();
    }

}
