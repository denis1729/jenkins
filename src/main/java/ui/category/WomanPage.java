package ui.category;

import entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;
import ui.HomePage;
import ui.order.Cart;

public abstract class WomanPage extends HomePage {
    @FindBy(css = "#center_column .product-container")
    private WebElement productLabel;

    @FindBy(css = ".button-container>a[title='View']")
    private WebElement productBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager web
     */
    protected WomanPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public abstract void goToPage();

    public abstract WomanPage goToSubcategoryPage(String subcategory);

    public Cart selectProduct() {
        driverTools.moveToElementMouse(productLabel);
        driverTools.clickElement(productBtn);
        return new Cart(this.webDriverManager);
    }

    public Cart selectProduct(Product product) {
        String path = "//span[contains(text(),'%s')]//parent::*//parent::*//*[@itemprop='name']//*[@title = '%s']";
        driverTools.moveToElementMouse(By.xpath(String.format(path, product.getPrice(), product.getProductName())));
        driverTools.clickElement(productBtn);
        return new Cart(this.webDriverManager);
    }
}
