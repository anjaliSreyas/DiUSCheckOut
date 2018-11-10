package rules;

import core.IPricingRule;
import core.Item;
import core.PricingRuleResponse;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BulkDiscount implements IPricingRule {

    private Item item;
    private int minQty;
    private BigDecimal discountPrice;

    public BulkDiscount(Item item, int minQty, BigDecimal discountPrice) {

        this.item = item;
        this.minQty = minQty;
        this.discountPrice = discountPrice;
    }

    @Override
    public PricingRuleResponse evaluate(Map<Item, Integer> cartContents) {
        BigDecimal totalPrice = new BigDecimal(0);
        Map<Item, Integer> processedItems = new HashMap<Item, Integer>();

        if (cartContents.containsKey(item))
        {
            Integer offerItemCount = cartContents.get(item);
            if(offerItemCount >= minQty){
                totalPrice = discountPrice.multiply(new BigDecimal(offerItemCount));
                processedItems.put(item, offerItemCount);
            }
        }

        return new PricingRuleResponse(totalPrice, processedItems, new HashMap<>());
    }
}
