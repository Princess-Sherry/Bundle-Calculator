package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Breakdown {
    private int numberOfBundles;
    private int bundleUnit;
    private double subTotal;

    @Override
    public String toString() {
        return (" " + numberOfBundles + " x " + bundleUnit + " $" + subTotal + "\n");
    }
}
