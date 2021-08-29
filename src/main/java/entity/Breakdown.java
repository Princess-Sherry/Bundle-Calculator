package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
