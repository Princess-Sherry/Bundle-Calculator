package services;

import entities.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.logging.Logger;


public class CalculationService {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    /**
     * Calculate the bundle combinations according to orders and bundle prices given, and their total costs and subtotal costs
     * @param orders orders input
     * @param bundles bundle prices input
     * @return bundle combinations results with costs
     */
    public List<BundleCombo> calculateBundleCombos(List<Order> orders, List<Bundle> bundles) {
        List<BundleCombo> bundleCombos = new LinkedList<>();

        orders.forEach(order -> {
            LOGGER.info("Calculating bundle combo for order " + order.getAmount() + " " + order.getFormat());
            bundles.forEach(bundle -> {
                if (bundle.getFormatCode().equals(order.getFormat())) {
                    int targetAmount = order.getAmount();
                    List<Integer> amounts = bundle.getBundleItems().stream().map(BundleItem::getBundleVolume).collect(Collectors.toList());
                    BundleComboCalculator bundleComboCalculator = new BundleComboCalculator();
                    HashMap<Integer, Integer> combo = bundleComboCalculator.getCombo(targetAmount, amounts);

                    List<BundleComboItem> bundleComboItems = new LinkedList<>();
                    combo.forEach( (bundleUnit, number) -> bundle.getBundleItems().forEach(bundleItem -> {
                        if (bundleUnit == bundleItem.getBundleVolume()) {
                            double subTotal = number * bundleItem.getPrice();
                            bundleComboItems.add(new BundleComboItem(number, bundleUnit, subTotal));
                        }
                    }));

                    double totalCost = bundleComboItems.stream().mapToDouble(BundleComboItem::getSubTotal).sum();
                    bundleCombos.add(new BundleCombo(targetAmount, order.getFormat(), totalCost, bundleComboItems));
                }
            });
        });

        return bundleCombos;
    }
}
