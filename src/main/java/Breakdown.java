import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * A class for each cost breakdown record for a certain type of order
 */
@Getter @Setter
@AllArgsConstructor
public class Breakdown {
    private int numberOfBundles;
    private int bundleUnit;
    private double subTotal;

    @Override
    public String toString() {
        return (" " + numberOfBundles + " x " + bundleUnit + " $" + subTotal);
    }
}

