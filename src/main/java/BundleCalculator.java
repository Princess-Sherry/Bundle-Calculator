import service.OrderService;
import service.PriceListService;
import service.ReportService;

/**
 * Main interface for the Bundle Calculator
 */
public class BundleCalculator {
    private OrderService os = new OrderService();
    private PriceListService ps = new PriceListService();
    private ReportService rs = new ReportService();

    /**
     * Main method to run the bundle calculator
     * @param args the first argument is the price list file path; the second argument is the order file path
     */
    public static void main(String[] args) {
        BundleCalculator bc = new BundleCalculator();
        bc.ps.updatePriceListFromFile(args[0]);
        bc.os.loadOrderFile(args[1]);
        bc.rs.calculateAndPrintCost(bc.os, bc.ps);
    }
}
