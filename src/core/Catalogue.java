package core;

import java.util.HashMap;
import java.util.Map;

public class Catalogue{
    private Map<String, Item> products = new HashMap<>();

    public void addProduct(Item product) {
        if(product==null) {
            return;
        }

        if(products.containsKey(product.getSku())){
            throw new IllegalArgumentException("Product with SKU "+product.getSku()+" already exists.");
        }

        products .put(product.getSku(), product);

    }

    public Item findProduct(String sku) {
        return products .getOrDefault(sku,null);
    }
}
