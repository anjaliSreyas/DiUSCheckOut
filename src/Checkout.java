import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Checkout {
    private List<PricingRule> pricingRules;
    private Map<Item, Integer> cartContents = new HashMap<>();

    public Checkout(List<PricingRule> pricingRules){
        this.pricingRules = pricingRules;
    }

    public void scan(Item item){
        if (!cartContents.containsKey(item)){
            cartContents.put(item, 1);
        }
        else {
            cartContents.put(item, cartContents.get(item) + 1);
        }
    }

    public BigDecimal total(){
        BigDecimal total = new BigDecimal(0);

        for (Map.Entry<Item, Integer> entry : cartContents.entrySet()) {
            total = total.add(entry.getKey().getPrice().multiply(new BigDecimal(entry.getValue())));
        }

        return total;
    }
}
