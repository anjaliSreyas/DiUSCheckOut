import core.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rules.BulkDiscount;
import rules.BundleFreeItem;
import rules.BuyMPayForN;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiUSExampleTests {

    private Catalogue catalogue = new Catalogue();
    private List<IPricingRule> pricingRules= new ArrayList<IPricingRule>();

    @BeforeEach
    public void SetUp(){
        catalogue.addProduct(new Item("ipd", new BigDecimal("549.99"),	"Super iPad"));
        catalogue.addProduct(new Item("mbp", new BigDecimal("1399.99"),	"MacBook Pro"));
        catalogue.addProduct(new Item("atv", new BigDecimal("109.50"),"Apple Tv"));
        catalogue.addProduct(new Item("vga", new BigDecimal("30"),"VGA Adapter"));

        pricingRules.add(new BuyMPayForN(catalogue.findProduct("atv"), 3, 2));
        pricingRules.add(new BulkDiscount(catalogue.findProduct("ipd"), 5, new BigDecimal("499.99")));
        pricingRules.add(new BundleFreeItem(catalogue.findProduct("mbp"), catalogue.findProduct("vga")));
    }


    @Test
    void DiUSExampleTest1(){
        Checkout checkout = new Checkout(pricingRules);

        checkout.scan(catalogue.findProduct("atv"));
        checkout.scan(catalogue.findProduct("atv"));
        checkout.scan(catalogue.findProduct("atv"));
        checkout.scan(catalogue.findProduct("vga"));

        assertEquals(0, checkout.total().compareTo(new BigDecimal("249.0")));
    }

    @Test
    void DiUSExampleTest2(){
        Checkout checkout = new Checkout(pricingRules);

        checkout.scan(catalogue.findProduct("atv"));
        checkout.scan(catalogue.findProduct("ipd"));
        checkout.scan(catalogue.findProduct("ipd"));
        checkout.scan(catalogue.findProduct("atv"));
        checkout.scan(catalogue.findProduct("ipd"));
        checkout.scan(catalogue.findProduct("ipd"));
        checkout.scan(catalogue.findProduct("ipd"));

        assertEquals(0, checkout.total().compareTo(new BigDecimal("2718.95")));
    }

    @Test
    void DiUSExampleTest3(){
        Checkout checkout = new Checkout(pricingRules);

        checkout.scan(catalogue.findProduct("mbp"));
        checkout.scan(catalogue.findProduct("vga"));
        checkout.scan(catalogue.findProduct("ipd"));

        assertEquals(0, checkout.total().compareTo(new BigDecimal("1949.98")));
    }
}
