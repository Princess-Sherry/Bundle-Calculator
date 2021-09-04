import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.OrderService;
import service.PriceListService;
import service.ReportService;

import static org.junit.jupiter.api.Assertions.*;

public class BundleCalculatorTest {
    BundleCalculator bc;

    @BeforeEach
    void setup() {
        bc = new BundleCalculator();
    }

    @Test
    @DisplayName("Test bundle calculator")
    void testMain() {
        PriceListService ps = new PriceListService();
        ps.updatePriceListFromFile("src/test/resources/priceListTest.txt");
        OrderService os = new OrderService();
        os.loadOrderFile("src/test/resources/ordersTestValidContent.txt");
        ReportService rs = new ReportService();
        rs.calculateAndPrintCost(os,ps);
        assertEquals(3,rs.getReports().size());
        assertEquals(13027.5,rs.getReports().stream().mapToDouble(r -> r.getTotalCost()).sum());
    }
}
