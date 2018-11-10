package core;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkout implements ICheckout {
    private List<IPricingRule> pricingRules;
    private Map<Item, Integer> cartContents = new HashMap<>();

    public Checkout(List<IPricingRule> pricingRules){
        this.pricingRules = pricingRules;
    }

    @Override
    public void scan(Item item){
        if (!cartContents.containsKey(item)){
            cartContents.put(item, 1);
        }
        else {
            cartContents.put(item, cartContents.get(item) + 1);
        }
    }

    @Override
    public BigDecimal total(){
        BigDecimal total = new BigDecimal(0);
        Map<Item, Integer> processedItems = new HashMap<>();
        Map<Item, Integer> freeItems = new HashMap<>();

        for (IPricingRule pricingRule : pricingRules) {
            PricingRuleResponse response = pricingRule.evaluate(cartContents);
            total = total.add(response.getSubTotal());
            mergeMap(response.getProcessedItems(), processedItems);
            mergeMap(response.getFreeItems(), freeItems);
        }

        Map<Item,Integer> pending = getPendingItems(cartContents, processedItems);

        for (Map.Entry<Item, Integer> entry : pending.entrySet()) {
            total = total.add(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
        }

        mergeMap(freeItems, cartContents);

        return total;
    }

    @Override
    public Map<Item, Integer> getCartContents() {
        return cartContents;
    }

    private void mergeMap(Map<Item, Integer> source, Map<Item, Integer> target)
    {
        for (Map.Entry<Item, Integer> sourceKvp : source.entrySet()) {
            if (target.containsKey(sourceKvp.getKey())){
                target.put(sourceKvp.getKey(), target.get(sourceKvp.getKey()) + sourceKvp.getValue());
            }
            else {
                target.put(sourceKvp.getKey(), sourceKvp.getValue());
            }
        }
    }

    private Map<Item, Integer> getPendingItems(Map<Item, Integer> cart, Map<Item, Integer> processedItems)
    {
        Map<Item, Integer> pending = new HashMap<>(cart);

        for (Map.Entry<Item, Integer> processedKvp : processedItems.entrySet()) {
            pending.put(processedKvp.getKey(), cart.get(processedKvp.getKey()) - processedKvp.getValue());
        }

        return pending;
    }
}
