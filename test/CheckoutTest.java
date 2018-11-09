import com.sun.tools.javac.comp.Check;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    @Test
    void NoOfferSimpleTotalTest() {

        Catalogue catalogue = new Catalogue();
        catalogue.addProduct(new Item("vga",new BigDecimal(30),"VGA Adapter"));

        Checkout checkout = new Checkout(new ArrayList<>());
        checkout.scan(catalogue.findProduct("vga"));
        checkout.scan(catalogue.findProduct("vga"));
        assertEquals(0, checkout.total().compareTo(new BigDecimal(60.0)));
    }
}