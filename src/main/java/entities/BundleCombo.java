package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BundleCombo {
    private int targetAmount;
    private String format;
    private List<BundleComboItem> bundleComboItems;

    public double getTotalCost() {
        return bundleComboItems.stream().mapToDouble(BundleComboItem::getSubTotal).sum();
    }
}
