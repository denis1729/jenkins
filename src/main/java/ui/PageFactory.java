package ui;

import selenium.WebDriverManager;
import ui.category.DressesPage;
import ui.category.TopsPage;
import ui.category.WomanPage;
import ui.category.subcategory.*;
import ui.order.PayBankCheck;
import ui.order.PayCheck;
import ui.order.PaymentPage;

public class PageFactory {

    //****************************************************************
    // Pages
    //****************************************************************

    public static WomanPage getCategory(final String category, WebDriverManager webDriverManager) {
        switch (category) {
            case "dresses":
                return new DressesPage(webDriverManager);

            default:
                return new TopsPage(webDriverManager);
        }
    }

    public static TopsPage getTops(final String subCategory, WebDriverManager webDriverManager) {
        switch (subCategory) {
            case "t-shirts":
                return new TShirts(webDriverManager);
            default:
                return new Blouses(webDriverManager);
        }
    }

    public static DressesPage getDresses(final String subCategory, WebDriverManager webDriverManager) {
        switch (subCategory) {
            case "casual dresses":
                return new CasualDresses(webDriverManager);
            case "evening dresses":
                return new EveningDresses(webDriverManager);
            default:
                return new SummerDresses(webDriverManager);
        }
    }

    public static PaymentPage getPayment(String payment, WebDriverManager webDriverManager) {
        switch (payment) {
            case "bank wire":
                return new PayBankCheck(webDriverManager);
            default:
                return new PayCheck(webDriverManager);
        }
    }
}
