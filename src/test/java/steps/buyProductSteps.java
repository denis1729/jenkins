package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Container;
import entities.Product;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import ui.PageFactory;

import ui.ProductPage;
import ui.category.WomanPage;
import ui.order.*;
import ui.popup.CartPopup;
import utils.LoggerSingleton;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class buyProductSteps {
    private final Container container;
    private final Logger log = LoggerSingleton.getInstance().getLogger(getClass().getName());
    private WomanPage womanPage;
    private Cart cart;
    private ProductPage productPage;
    private CartPopup cartPopup;
    private AddressPage addressPage;
    private ShoppingSummary shoppingSummary;
    private ShippingPage shippingPage;
    private PaymentPage paymentPage;

    private Product product;

    public buyProductSteps(Container container) {
        this.container = container;
    }

    @When("^I go to the women's category$")
    public void iGoToTheWomenSCategory() {
        container.homePage.goToWomenPage();
    }

    @And("^I select the product (.*?) with the subcategory of (.*?)$")
    public void iSelectTheProductWithTheSubcategoryOf(String category, String subcategory) {
        womanPage = PageFactory.getCategory(category, container.pageTransporter.getWebDriverManager());
        womanPage.goToPage();
        womanPage = womanPage.goToSubcategoryPage(subcategory);
        womanPage.goToPage();
    }

    @And("^I add one product to the cart$")
    public void iAddTheProductToTheCart() {
        cart = womanPage.selectProduct();
        cart.addItemToCart();
        shoppingSummary = cart.proceedCheckoutShopping();
        addressPage = shoppingSummary.proceedCheckoutAddress();
        shippingPage = addressPage.proceedCheckoutShipping();
        shippingPage.agreeCheckbox();
        shippingPage.proceedCheckoutShipping();
    }

    @And("^I add the following product to the cart$")
    public void iAddTheFollowingProductToTheCart(Map<String, String> object) {
        product = new ObjectMapper().convertValue(object, Product.class);
        productPage = womanPage.selectProduct(product);
        cartPopup = productPage.addToCart(product);
        shoppingSummary = cartPopup.proceedCheckoutShopping();
        addressPage = shoppingSummary.proceedCheckoutAddress();
        shippingPage = addressPage.proceedCheckoutShipping();
        shippingPage.agreeCheckbox();
        shippingPage.proceedCheckoutShipping();
    }

    @And("^I proceed to pay the product with (.*?)$")
    public void iProceedToPayTheProductWith(String payment) {
        paymentPage = PageFactory.getPayment(payment, container.pageTransporter.getWebDriverManager());
        paymentPage.selectMethodPay();
        paymentPage.clickConfirmButton();

    }

    @Then("^I should buy the product successfully and show the following message$")
    public void iShouldBuyTheProductSuccessfullyAndShowThe(String message) {
        String actual = paymentPage.getBuyMessage();
        log.info("Actual message: {} and expected message {}", actual, message);
        assertEquals(actual, message, "The message not equals");
    }

    @Then("^The total price should be equals to price by quantity more the shipping (.*?) US$")
    public void totalPriceShouldBe(double shipping) {
        String actual = paymentPage.getAmount();
        String expected = NumberFormat.getCurrencyInstance(Locale.US).
                format(product.getPrice() * product.getQuantity() + shipping);
        log.info("Actual amount: {} and expected amount {}", actual, expected);
        assertEquals(actual, expected, "the total price not equals");
    }
}
