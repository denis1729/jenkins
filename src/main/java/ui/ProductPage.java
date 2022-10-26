package ui;

import entities.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.WebDriverManager;
import ui.popup.CartPopup;

public class ProductPage extends HomePage {
    @FindBy(css = "input[name='qty']")
    private WebElement quantityInput;

    @FindBy(css = "#add_to_cart>button")
    private WebElement addToCartBtn;

    private final By  sizeList = By.cssSelector(".attribute_list select");

    /**
     * Initializes the web driver, wait, web driver tools and web elements.
     *
     * @param webDriverManager driver
     */
    public ProductPage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }

    /**
     * This method add the product in the cart
     *
     * @param product product
     * @return popup cart
     */
    public CartPopup addToCart(Product product) {
        if (product.getQuantity() > 0)
            driverTools.setInputField(quantityInput, String.valueOf(product.getQuantity()));
        if (product.getSize() != null)
            driverTools.selectListBoxByVisibleText(sizeList, product.getSize());
        if (product.getColor() != null) {
            String color = ".attribute_list [id*='color'][name='%s']";
            driverTools.clickElement(By.cssSelector(String.format(color, product.getColor())));
        }
        driverTools.clickElement(addToCartBtn);
        return new CartPopup(webDriverManager);
    }
}
