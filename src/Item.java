import java.math.BigDecimal;
import java.util.Objects;

public class Item {
    private String sku;
    private BigDecimal price;
    private String name;

    public Item(String sku, BigDecimal price, String name){
        this.sku=sku;
        this.price = price;
        this.name=name;
    }

    public String getSku() {
        return sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Item)) {
            return false;
        }

        Item product = (Item) o;

        return Objects.equals(getSku(), product.getSku()) &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getPrice(), product.getPrice());
    }


    @Override
    public int hashCode() {
        return Objects.hash(getSku());
    }
}
