package ui.category.subcategory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;
import ui.category.TopsPage;

public class Blouses extends TopsPage {

    @FindBy(xpath = "//*[@id='subcategories']//child::a[text()='Blouses']")
    private WebElement blousesBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager driver
     */
    public Blouses(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    private void clickBlousesButton() {
        driverTools.clickElement(blousesBtn);
    }

    @Override
    public void goToPage() {
        clickBlousesButton();
    }
}
