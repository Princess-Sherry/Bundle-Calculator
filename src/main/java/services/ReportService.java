package services;

import entities.BundleCombo;
import java.util.*;

/**
 * This services.ReportService Class is to print out the cost reports according to the bundle combos results
 */

public class ReportService {
    public void printReports(List<BundleCombo> bundleCombos) {
        bundleCombos.forEach(bundleCombo -> {
            System.out.println(bundleCombo.getTargetAmount() + " " + bundleCombo.getFormat() + " $" + bundleCombo.getTotalCost());
            bundleCombo.getBundleComboItems().forEach(item -> System.out.println(" " + item.getNumberOfBundles() + " x " + item.getBundleUnit() + " $" + item.getSubTotal()));
        });
    }
}
