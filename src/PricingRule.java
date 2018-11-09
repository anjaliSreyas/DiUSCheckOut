public class PricingRule {

    private String sku;
    private IOffer offer;

    public PricingRule(String sku, IOffer offer){
        this.sku = sku;
        this.offer = offer;
    }

    String getSku(){
        return sku;
    }

    IOffer getOffer(){
        return offer;
    }
}
