package rules;

import core.IPricingRule;
import core.Item;
import core.PricingRuleResponse;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class BundleFreeItem implements IPricingRule {

    private Item item;
    private Item freeItem;

    public BundleFreeItem(Item item, Item freeItem) {

        this.item = item;
        this.freeItem = freeItem;
    }

    @Override
    public PricingRuleResponse evaluate(Map<Item, Integer> cartContents) {
        BigDecimal totalPrice = new BigDecimal(0);
        Map<Item, Integer> freeItems = new HashMap<Item, Integer>();
        Map<Item, Integer> processedItems = new HashMap<Item, Integer>();

        if (cartContents.containsKey(item))
        {
            if(cartContents.containsKey(freeItem)){
                Integer feeeItemCount=cartContents.get(freeItem);
                Integer offerItemCount=cartContents.get(item);
                if(feeeItemCount >=offerItemCount){
                    processedItems.put(freeItem,offerItemCount);
                    }
                    else{

                    processedItems.put(freeItem,feeeItemCount+(offerItemCount-offerItemCount));
                }
            }else {
                freeItems.put(freeItem, cartContents.get(item));
            }
        }

        return new PricingRuleResponse(totalPrice, processedItems, freeItems);
    }
}
