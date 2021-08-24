import lombok.Getter;

@Getter
public class Breakdown {
    private int numberOfBundles;
    private int bundleUnit;
    private double subCost;

    public Breakdown(int numberOfBundles, int bundleUnit, double subCost) {
        this.numberOfBundles = numberOfBundles;
        this.bundleUnit = bundleUnit;
        this.subCost = subCost;
    }
}
