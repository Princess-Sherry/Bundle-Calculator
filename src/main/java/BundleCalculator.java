import service.CostService;
import service.OrderService;
import service.PriceListService;
import java.io.IOException;

public class BundleCalculator {
    private OrderService os = new OrderService();
    private PriceListService ps = new PriceListService();
    private CostService cs = new CostService();

    public static void main(String[] args) throws IOException {
        BundleCalculator bc = new BundleCalculator();
        bc.ps.updatePriceListFromFile("src/main/resources/priceList.txt");
        bc.os.loadOrderFile("src/main/resources/orders.txt");
        bc.cs.calculateAndPrintCost(bc.os, bc.ps);
    }
}
