import lombok.Getter;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This ReportService Class is to run the calculation and then print out the cost reports
 */
@Getter
public class ReportService {
    private final ArrayList<Report> reports = new ArrayList<>();
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    /**
     * Calculate total cost and sub-costs according to orders and bundle prices input given, then print out reports
     * @param os orders input
     * @param bs bundle prices input
     */
    public void calculateAndPrintCost(OrderService os, BundleService bs) {
        os.getOrders().forEach((orderFormatKey,targetAmountValue) -> {
            LOGGER.info("Calculating costs for order " + targetAmountValue + " " + orderFormatKey);
            bs.getBundles().forEach((bundle) -> {
                if (bundle.getFormatCode().equals(orderFormatKey)) {
                    calculateCost(orderFormatKey,targetAmountValue,bundle.getBundleItems());
                }
            });
        });
        LOGGER.info("Print report to show costs for the orders");
        reports.forEach((report -> System.out.print(report.toString())));
    }

    private void calculateCost(String formatCode, int targetAmount, ArrayList<BundleItem> bundleItems) {
        List<Integer> amounts = bundleItems.stream().map(b -> b.getBundleVolume()).collect(Collectors.toList());
        HashMap<Integer, Integer> bundleCombination = new BundleComboCalculator(targetAmount,amounts).getBundleCombination();

        ArrayList<Breakdown> breakdowns = new ArrayList<>();
        bundleCombination.forEach((bundleUnit,amount) -> {
            bundleItems.forEach(bundleItem -> {
                if (bundleItem.getBundleVolume() == bundleUnit) {
                    double price = bundleItem.getPrice();
                    double subTotal = amount * price;
                    breakdowns.add(new Breakdown(amount,bundleUnit,subTotal));
                }
            });
        });

        breakdowns.sort(Comparator.comparing(Breakdown::getBundleUnit).reversed());

        double totalCost = breakdowns.stream().mapToDouble(Breakdown::getSubTotal).sum();

        reports.add(new Report(targetAmount,formatCode,totalCost,breakdowns));
    }
}
