package rules;

import core.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BuyMPayForN implements IPricingRule {

    private Item item;
    private Integer m;
    private Integer n;

    public BuyMPayForN(Item item, Integer m, Integer n)
    {
        this.item = item;
        this.m = m;
        this.n = n;
    }

    @Override
    public PricingRuleResponse evaluate(Map<Item, Integer> cartContents){

        BigDecimal totalPrice = new BigDecimal(0);
        Map<Item, Integer> freeItems = new HashMap<Item, Integer>();
        Map<Item, Integer> processedItems = new HashMap<Item, Integer>();

        if (cartContents.containsKey(item))
        {
            Integer numSets = cartContents.get(item) / m;
            totalPrice = new BigDecimal(numSets).multiply(new BigDecimal(n)).multiply(item.getPrice());
            processedItems.put(item, numSets * m);
        }

        return new PricingRuleResponse(totalPrice, processedItems, freeItems);
    }
}
