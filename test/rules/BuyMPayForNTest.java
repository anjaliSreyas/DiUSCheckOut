package rules;

import core.Item;
import core.PricingRuleResponse;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class BuyMPayForNTest {

    private BuyMPayForN pricingRule;
    private Item offerItem;
    private Item nonOfferItem;

    @BeforeEach
    void setUp() {
        offerItem = new Item("kbd", new BigDecimal(20.0), "Keyboard");
        nonOfferItem = new Item("mse", new BigDecimal(25.0), "Mouse");
        pricingRule = new BuyMPayForN(offerItem, 5, 4);
    }

    @Test
    void basicTest(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(offerItem, 5);
        items.put(nonOfferItem, 5);

        PricingRuleResponse response = pricingRule.evaluate(items);

        assertTrue(response.getFreeItems().isEmpty());
        assertTrue(response.getTotalPrice().compareTo(new BigDecimal(80.0)) == 0);
        assertEquals(1, response.getProcessedItems().size());
        assertEquals(new Integer(5), response.getProcessedItems().get(offerItem));
    }

    @Test
    void multiplesTest(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(offerItem, 15);
        items.put(nonOfferItem, 5);

        PricingRuleResponse response = pricingRule.evaluate(items);

        assertTrue(response.getFreeItems().isEmpty());
        assertTrue(response.getTotalPrice().compareTo(new BigDecimal(240.0)) == 0);
        assertEquals(1, response.getProcessedItems().size());
        assertEquals(new Integer(15), response.getProcessedItems().get(offerItem));
    }

    @Test
    void sparesTest(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(offerItem, 17);
        items.put(nonOfferItem, 5);

        PricingRuleResponse response = pricingRule.evaluate(items);

        assertTrue(response.getFreeItems().isEmpty());
        assertTrue(response.getTotalPrice().compareTo(new BigDecimal(240.0)) == 0);
        assertEquals(1, response.getProcessedItems().size());
        assertEquals(new Integer(15), response.getProcessedItems().get(offerItem));
    }

    @Test
    void voidTest(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(nonOfferItem, 5);

        PricingRuleResponse response = pricingRule.evaluate(items);

        assertTrue(response.getFreeItems().isEmpty());
        assertTrue(response.getTotalPrice().compareTo(new BigDecimal(0.0)) == 0);
        assertTrue(response.getProcessedItems().isEmpty());
    }
}