package rules;

import core.Item;
import core.PricingRuleResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BulkDiscountTest {
    private BulkDiscount pricingRule;
    private Item offerItem;
    private Item nonOfferItem;

    @BeforeEach
    void setUp() {
        offerItem = new Item("mbp", new BigDecimal(2000.0), "MacBook Pro");
        nonOfferItem = new Item("mse", new BigDecimal(25.0), "Mouse");
        pricingRule = new BulkDiscount(offerItem, 4, new BigDecimal(1500));
    }

    @Test
    void basicTest(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(offerItem, 4);
        items.put(nonOfferItem, 5);

        PricingRuleResponse response = pricingRule.evaluate(items);

        assertTrue(response.getFreeItems().isEmpty());
        assertTrue(response.getSubTotal().compareTo(new BigDecimal(6000.0)) == 0);
        assertEquals(1, response.getProcessedItems().size());
        assertEquals(new Integer(4), response.getProcessedItems().get(offerItem));
    }

    @Test
    void multiplesTest(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(offerItem, 8);
        items.put(nonOfferItem, 5);

        PricingRuleResponse response = pricingRule.evaluate(items);

        assertTrue(response.getFreeItems().isEmpty());
        assertTrue(response.getSubTotal().compareTo(new BigDecimal(12000.0)) == 0);
        assertEquals(1, response.getProcessedItems().size());
        assertEquals(new Integer(8), response.getProcessedItems().get(offerItem));
    }


    @Test
    void offerNotApplicableTest(){
        Map<Item, Integer> items = new HashMap<Item, Integer>();
        items.put(offerItem, 3);
        items.put(nonOfferItem, 5);

        PricingRuleResponse response = pricingRule.evaluate(items);

        assertTrue(response.getFreeItems().isEmpty());
        assertTrue(response.getSubTotal().compareTo(new BigDecimal(0)) == 0); // no items priced within this offer
        assertEquals(0, response.getProcessedItems().size());
    }

}