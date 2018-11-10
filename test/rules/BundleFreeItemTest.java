package rules;

import core.Item;
import core.PricingRuleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BundleFreeItemTest {
    private BundleFreeItem pricingRule;
    private Item offerItem;
    private Item freeItem;
    private Item nonOfferItem;

    @BeforeEach
    void setUp() {
        offerItem = new Item("mbp", new BigDecimal(2000.0), "MacBook Pro");
        freeItem = new Item("vga", new BigDecimal(100.0), "VGA Adapter");
        nonOfferItem = new Item("mse", new BigDecimal(25.0), "Mouse");
        pricingRule = new BundleFreeItem(offerItem, freeItem);
    }

    @Test
    void offerNotApplicable(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(nonOfferItem, 5);
        PricingRuleResponse response = pricingRule.evaluate(items);

        assertTrue(response.getFreeItems().isEmpty());
        assertTrue(response.getSubTotal().compareTo(new BigDecimal(0)) == 0); // no items priced within this offer
        assertEquals(0, response.getProcessedItems().size());
    }

    @Test
    void itemBundledForFree(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(nonOfferItem, 5);
        items.put(offerItem, 2);
        PricingRuleResponse response = pricingRule.evaluate(items);

        assertEquals(1, response.getFreeItems().size());
        assertEquals(new Integer(2), response.getFreeItems().get(freeItem));
        assertTrue(response.getSubTotal().compareTo(new BigDecimal(0)) == 0);
        assertEquals(0, response.getProcessedItems().size());
    }

    @Test
    void itemBundledForFreeImplicity(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(nonOfferItem, 5);
        items.put(offerItem, 3);
        items.put(freeItem, 3);
        PricingRuleResponse response = pricingRule.evaluate(items);

        assertEquals(0, response.getFreeItems().size());
        assertTrue(response.getSubTotal().compareTo(new BigDecimal(0)) == 0);
        assertEquals(1, response.getProcessedItems().size());
        assertEquals(new Integer(3), response.getProcessedItems().get(freeItem));
    }

}