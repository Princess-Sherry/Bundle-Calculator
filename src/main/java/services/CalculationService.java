package services;

import entities.*;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@AllArgsConstructor
public class CalculationService {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private BundleComboCalculator bundleComboCalculator;

    /**
     * Calculate the bundle combinations according to order and bundle prices given, and their total costs and subtotal costs
     *
     * @param order  order input
     * @param bundles bundle prices input
     * @return bundle combinations results with costs
     */
    public List<BundleCombo> calculateBundleCombos(List<OrderItem> order, List<Bundle> bundles) {
        List<BundleCombo> bundleCombos = new LinkedList<>();

        order.forEach(orderItem -> {
            LOGGER.info("Calculating bundle combo for order " + orderItem.getAmount() + " " + orderItem.getFormat());
            bundles.forEach(bundle -> {
                if (bundle.getFormatCode().equals(orderItem.getFormat())) {
                    int targetAmount = orderItem.getAmount();
                    List<Integer> amounts = bundle.getBundleItems().stream().map(BundleItem::getBundleVolume).collect(Collectors.toList());
                    HashMap<Integer, Integer> combo = bundleComboCalculator.getCombo(targetAmount, amounts);

                    List<BundleComboItem> bundleComboItems = new LinkedList<>();
                    combo.forEach((bundleUnit, number) -> bundle.getBundleItems().forEach(bundleItem -> {
                        if (bundleUnit == bundleItem.getBundleVolume()) {
                            double subTotal = number * bundleItem.getPrice();
                            bundleComboItems.add(new BundleComboItem(number, bundleUnit, subTotal));
                        }
                    }));

                    double totalCost = bundleComboItems.stream().mapToDouble(BundleComboItem::getSubTotal).sum();
                    bundleCombos.add(new BundleCombo(targetAmount, orderItem.getFormat(), totalCost, bundleComboItems));
                }
            });
        });

        return bundleCombos;
    }
}
