import java.util.logging.Logger;

/**
 * Main interface for the Bundle Calculator
 */
public class BundleCalculator {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private final OrderService os = new OrderService();
    private final BundleService bs = new BundleService();
    private final CalculationService cs = new CalculationService();
    private ReportService rs = new ReportService();

    /**
     * Main method to run the bundle calculator
     * @param args the first argument is the bundle price file path; the second argument is the order file path
     */
    public static void main(String[] args) {
        BundleCalculator bc = new BundleCalculator();

        // default files paths
        String bundleFilePath = "src/main/resources/bundles.txt";
        String orderFilePath = "src/main/resources/orders.txt";

        // custom files paths from user
        if (args.length == 2) {
            bundleFilePath = args[0];
            orderFilePath = args[1];
        }

        // invalid number of arguments -> exit the program
        if (args.length != 2 && args.length != 0) {
            LOGGER.severe("Two arguments are required. Please try again.");
            System.exit(0);
        }

        bc.bs.updatePriceListFromFile(bundleFilePath);
        bc.os.loadOrderFile(orderFilePath, bc.bs);
        bc.rs = bc.cs.calculateCost(bc.os, bc.bs);
        bc.rs.printReports();
    }
}
