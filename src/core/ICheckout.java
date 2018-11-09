package core;

import java.math.BigDecimal;
import java.util.Map;

public interface ICheckout {
    void scan(Item item);

    BigDecimal total();

    Map<Item, Integer> getCartContents();
}
