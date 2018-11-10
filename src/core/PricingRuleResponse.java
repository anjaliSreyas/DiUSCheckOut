package core;

import com.sun.tools.javac.jvm.Items;

import java.math.BigDecimal;
import java.util.Map;

public class PricingRuleResponse {

    private BigDecimal subTotal;
    private Map<Item, Integer> freeItems;
    private Map<Item, Integer> processedItems;

    public PricingRuleResponse(BigDecimal subTotal,
                               Map<Item, Integer> processedItems,
                               Map<Item, Integer> freeItems){

        this.subTotal = subTotal;
        this.freeItems = freeItems;
        this.processedItems = processedItems;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public Map<Item, Integer> getFreeItems() {
        return freeItems;
    }

    public Map<Item, Integer> getProcessedItems() {
        return processedItems;
    }
}
