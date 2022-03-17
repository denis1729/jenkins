package ui;

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

    public static WomanPage getCategory(final String category) {
        switch (category) {
            case "dresses":
                return new DressesPage();

            default:
                return new TopsPage();
        }
    }

    public static TopsPage getTops(final String subCategory) {
        switch (subCategory) {
            case "t-shirts":
                return new TShirts();
            default:
                return new Blouses();
        }
    }

    public static DressesPage getDresses(final String subCategory) {
        switch (subCategory) {
            case "casual dresses":
                return new CasualDresses();
            case "evening dresses":
                return new EveningDresses();
            default:
                return new SummerDresses();
        }
    }

    public static PaymentPage getPayment(String payment) {
        switch (payment) {
            case "bank wire":
                return new PayBankCheck();
            default:
                return new PayCheck();
        }
    }
}
