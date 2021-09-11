package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BundleCombo {
    private int targetAmount;
    private String format;
    private double totalCost;
    private List<BundleComboItem> bundleComboItems;
}
