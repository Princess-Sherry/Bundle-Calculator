package entity.output;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Breakdown {
    private int numberOfBundles;
    private int bundleUnit;
    private double subCost;
}
