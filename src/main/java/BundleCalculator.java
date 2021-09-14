import entities.Bundle;
import entities.BundleCombo;
import entities.OrderItem;
import exceptions.DataFormatException;
import services.*;

import java.io.IOException;
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
            String orderFilePath = "src/main/resources/order.txt";

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

            LOGGER.info("Start reading order from file " + orderFilePath);
            OrderService orderService = new OrderService();
            List<OrderItem> order = orderService.loadOrderFile(orderFilePath);
            LOGGER.info("Finish reading order from file " + orderFilePath);

            BundleComboCalculator bundleComboCalculator = new BundleComboCalculator();
            CalculationService calculationService = new CalculationService(bundleComboCalculator);
            List<BundleCombo> bundleCombos = calculationService.calculateBundleCombos(order, bundles);
            LOGGER.info("Finish calculating bundle combos");

            LOGGER.info("Printing reports for the cost and bundle breakdown for each submission format");
            ReportService reportService = new ReportService();
            reportService.printReports(bundleCombos);

        } catch (DataFormatException | IOException e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
            System.exit(0);
        } catch (NumberFormatException e) {
            LOGGER.severe(e.getMessage() + ": invalid number format.");
            e.printStackTrace();
            System.exit(0);
        }
    }
}

