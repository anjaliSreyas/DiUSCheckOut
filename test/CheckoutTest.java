import core.Catalogue;
import core.Checkout;
import core.IPricingRule;
import core.Item;
import org.junit.jupiter.api.Test;
import rules.BuyMPayForN;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    @Test
    void NoOfferSimpleTotalTest() {

        Catalogue catalogue = new Catalogue();
        catalogue.addProduct(new Item("vga",new BigDecimal(30),"VGA Adapter"));

        Checkout checkout = new Checkout(new ArrayList<>());
        checkout.scan(catalogue.findProduct("vga"));
        checkout.scan(catalogue.findProduct("vga"));
        checkout.scan(catalogue.findProduct("vga"));
        assertEquals(0, checkout.total().compareTo(new BigDecimal(90.0)));
    }

    @Test
    void BuyMPayForNOfferIntegrationTest() {

        Catalogue catalogue = new Catalogue();
        Item vga = new Item("vga",new BigDecimal(30),"VGA Adapter");
        catalogue.addProduct(vga);

        List<IPricingRule> pricingRules = new ArrayList<IPricingRule>();
        pricingRules.add(new BuyMPayForN(vga, 3, 2));
        Checkout checkout = new Checkout(pricingRules);

        checkout.scan(catalogue.findProduct("vga"));
        checkout.scan(catalogue.findProduct("vga"));
        checkout.scan(catalogue.findProduct("vga"));

        assertEquals(0, checkout.total().compareTo(new BigDecimal(60.0)));
    }

    @Test
    void BuyMPayForNOfferWithSpareIntegrationTest() {

        Catalogue catalogue = new Catalogue();
        Item vga = new Item("vga",new BigDecimal(30),"VGA Adapter");
        catalogue.addProduct(vga);

        List<IPricingRule> pricingRules = new ArrayList<IPricingRule>();
        pricingRules.add(new BuyMPayForN(vga, 3, 2));
        Checkout checkout = new Checkout(pricingRules);

        checkout.scan(catalogue.findProduct("vga"));
        checkout.scan(catalogue.findProduct("vga"));
        checkout.scan(catalogue.findProduct("vga"));
        checkout.scan(catalogue.findProduct("vga"));

        assertEquals(0, checkout.total().compareTo(new BigDecimal(90.0)));
    }
}