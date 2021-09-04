import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class CalculationService {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private final ReportService rs= new ReportService();

    /**
     * Calculate total cost and sub-costs according to orders and bundle prices input given, then print out reports
     * @param os orders input
     * @param bs bundle prices input
     */
    public ReportService calculateCost(OrderService os, BundleService bs) {
        os.getOrders().forEach((orderFormat,targetAmount) -> {
            LOGGER.info("Calculating costs for order " + targetAmount + " " + orderFormat);
            bs.getBundles().forEach((bundle) -> {
                if (bundle.getFormatCode().equals(orderFormat)) {
                    ArrayList<Breakdown> breakdowns = calculateSubCost(targetAmount, bundle.getBundleItems());
                    double totalCost = breakdowns.stream().mapToDouble(Breakdown::getSubTotal).sum();
                    rs.getReports().add(new Report(targetAmount,orderFormat,totalCost,breakdowns));
                }
            });
        });
        return rs;
    }

    private ArrayList<Breakdown> calculateSubCost(int targetAmount, ArrayList<BundleItem> bundleItems) {
        List<Integer> amounts = bundleItems.stream().map(b -> b.getBundleVolume()).collect(Collectors.toList());
        HashMap<Integer, Integer> bundleCombination = new BundleComboCalculator(targetAmount,amounts).getBundleCombination();

        ArrayList<Breakdown> breakdowns= new ArrayList<>();
        bundleCombination.forEach((bundleUnit,amount) -> {
            bundleItems.forEach(bundleItem -> {
                if (bundleItem.getBundleVolume() == bundleUnit) {
                    double subTotal = amount * bundleItem.getPrice();;
                    breakdowns.add(new Breakdown(amount,bundleUnit,subTotal));
                }
            });
        });

        breakdowns.sort(Comparator.comparing(Breakdown::getBundleUnit).reversed());
        return breakdowns;
    }
}
