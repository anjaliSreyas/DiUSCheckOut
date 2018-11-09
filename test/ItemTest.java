import core.Item;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    public void testEquals() {
        Item product1=new Item("mbp",new BigDecimal(1399),"MackBook Pro");
        Item product2=new Item("mbp",new BigDecimal(1399),"MackBook Pro");
        assertTrue(product1.equals(product2));

    }

    @Test
    public void testUnEqual() {
        Item product1=new Item("mbp",new BigDecimal(1399),"MackBook Pro");
        Item product2=new Item("ipd",new BigDecimal(199),"ipad Pro");
        assertFalse(product1.equals(product2));

        Item product3 = new Item("mbp",new BigDecimal(1399),"MackBook Pro");
        Item product4 =new Item("mbp",new BigDecimal(199),"ipad Pro");
        assertFalse(product3.equals(product4));
    }

    @Test
    public void testHashCodesEqualForEqualObjects() {
        Item product1=new Item("mbp",new BigDecimal(1399),"MackBook Pro");
        Item product2 = new Item("mbp",new BigDecimal(1399),"MackBook Pro");
        assertTrue(product1.hashCode()== product2.hashCode());

    }

}