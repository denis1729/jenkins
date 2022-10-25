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

    private PageFactory() {
    }

    //****************************************************************
    // Pages
    //****************************************************************

    public static WomanPage getCategory(final String category, WebDriverManager webDriverManager) {
        if ("dresses".equals(category)) {
            return new DressesPage(webDriverManager);
        }
        return new TopsPage(webDriverManager);
    }

    public static TopsPage getTops(final String subCategory, WebDriverManager webDriverManager) {
        if ("t-shirts".equals(subCategory)) {
            return new TShirts(webDriverManager);
        }
        return new Blouses(webDriverManager);
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
        if ("bank wire".equals(payment)) {
            return new PayBankCheck(webDriverManager);
        }
        return new PayCheck(webDriverManager);
    }
}
