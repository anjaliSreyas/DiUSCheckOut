package core;

import java.util.Map;

public interface IPricingRule {

    PricingRuleResponse evaluate(Map<Item, Integer> cartContents);
}
