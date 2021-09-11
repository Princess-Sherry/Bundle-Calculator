import entities.Bundle;
import entities.BundleCombo;
import entities.Order;
import exceptions.DataAccessException;
import exceptions.DataFormatException;
import services.BundleService;
import services.CalculationService;
import services.OrderService;
import services.ReportService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Main interface for the Bundle Calculator
 */
public class BundleCalculator {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    /**
     * Main method to run the bundle calculator
     *
     * @param args the first argument is the bundle price file path; the second argument is the order file path
     */
    public static void main(String[] args) {
        try {
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

            LOGGER.info("Start reading bundle prices from file " + bundleFilePath);
            BundleService bundleService = new BundleService();
            List<Bundle> bundles = bundleService.loadBundleFile(bundleFilePath);
            LOGGER.info("Finish reading bundle prices from file " + bundleFilePath);

            LOGGER.info("Start reading orders from file " + orderFilePath);
            OrderService orderService = new OrderService();
            List<Order> orders = orderService.loadOrderFile(orderFilePath);
            LOGGER.info("Finish reading orders from file " + orderFilePath);

            CalculationService calculationService = new CalculationService();
            List<BundleCombo> bundleCombos = calculationService.calculateBundleCombos(orders, bundles);
            LOGGER.info("Finish calculating bundle combos");

            LOGGER.info("Printing reports for the cost and bundle breakdown for each submission format");
            ReportService reportService = new ReportService();
            reportService.printReports(bundleCombos);

        } catch (DataFormatException | DataAccessException e) {
            LOGGER.severe(e.getMessage());
            System.exit(0);
        }
    }
}

