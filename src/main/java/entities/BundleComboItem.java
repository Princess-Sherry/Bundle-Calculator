package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BundleComboItem {
    private int numberOfBundles;
    private int bundleUnit;
    private double price;

    public double getSubTotal() {
        return numberOfBundles * price;
    }
}
