import core.Catalogue;
import core.Item;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CatalogueTest {
    private Catalogue catalogue;

    @BeforeEach
    public void before() {

        catalogue = new Catalogue();
    }

    @Test
    public void findNonExistingProduct() {
        catalogue.addProduct(new Item("mbp", new BigDecimal(1800), "MacBook Pro"));
        assertNull(catalogue.findProduct("ipd"));
    }

    @Test
    public void findProduct() {
        catalogue.addProduct(new Item("ipd", new BigDecimal(1200.00), "Super iPad"));
        assertEquals("Super iPad", catalogue.findProduct("ipd").getName());
    }

    @Test
    public void duplicateSKU() {
        catalogue.addProduct(new Item("mbp", new BigDecimal(1900.00), "MacBook Pro"));

        try {
            catalogue.addProduct(new Item("mbp", new BigDecimal(2000.00), "MacBook Pro 2018"));
            fail("Exception expected but not thrown");
        } catch (IllegalArgumentException ex) {

        }
    }
}