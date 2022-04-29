package ui.category;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;
import ui.HomePage;
import ui.order.Cart;

public abstract class WomanPage extends HomePage {
    @FindBy(xpath = "//*[@id='center_column']/ul/li")
    private WebElement productBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager
     */
    public WomanPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public abstract void goToPage();

    public abstract WomanPage goToSubcategoryPage(String subcategory);

    private Cart clickProduct() {
        driverTools.clickElement(productBtn);
        return new Cart(this.webDriverManager);
    }

    public Cart selectProduct() {
        return clickProduct();
    }
}
