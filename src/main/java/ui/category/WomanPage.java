package ui.category;

import entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;
import ui.HomePage;
import ui.ProductPage;
import ui.order.Cart;

public abstract class WomanPage extends HomePage {
    @FindBy(css = "#center_column .product-container")
    private WebElement productLabel;

    @FindBy(css = ".button[title='View']")
    private WebElement productBtn;

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager driver
     */
    protected WomanPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    public abstract void goToPage();

    public abstract WomanPage goToSubcategoryPage(String subcategory);

    /**
     * This method select the first product item
     * @return cart page
     */
    public Cart selectProduct() {
        driverTools.moveToElementMouse(productLabel);
        driverTools.clickElement(productBtn);
        return new Cart(this.webDriverManager);
    }

    /**
     * This method select the product item
     *
     * @param product product
     * @return cart page
     */
    public ProductPage selectProduct(Product product) {
        String item = "//*[contains(text(),'$%s')]//ancestor::div[@class='content_price']//" +
                "ancestor::div[@class='product-container']//*[@class='product-image-container']//a[@title='%s']";
        driverTools.moveToElementMouse(By.xpath(String.format(item, product.getPrice(), product.getProductName())));
        driverTools.clickElement(productBtn);
        return new ProductPage(this.webDriverManager);
    }
}
