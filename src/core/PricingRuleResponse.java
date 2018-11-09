package core;

import java.math.BigDecimal;
import java.util.Map;

public class PricingRuleResponse {

    private BigDecimal totalPrice;
    private Map<Item, Integer> freeItems;
    private Map<Item, Integer> processedItems;

    public PricingRuleResponse(BigDecimal totalPrice,
                               Map<Item, Integer> processedItems,
                               Map<Item, Integer> freeItems){

        this.totalPrice = totalPrice;
        this.freeItems = freeItems;
        this.processedItems = processedItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Map<Item, Integer> getFreeItems() {
        return freeItems;
    }

    public Map<Item, Integer> getProcessedItems() {
        return processedItems;
    }
}
