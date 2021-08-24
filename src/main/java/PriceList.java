import lombok.Getter;

@Getter
public class PriceList {
    private int bundles;
    private double price;
    private String format;

    public PriceList(int bundles, double price, String format) {
        this.bundles = bundles;
        this.price = price;
        this.format = format;
    }
}
