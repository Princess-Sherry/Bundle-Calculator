import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BundleCalculatorTest {

    @BeforeEach
    public void setup() {
        BundleCalculator bc = new BundleCalculator();
    }

    @Test
    @DisplayName("Test bundle calculator")
    public void testMain() throws DataFormatException, DataAccessException {
        BundleService bs = new BundleService();
        bs.updatePriceListFromFile("src/test/resources/bundlesTestValidContent.txt");
        OrderService os = new OrderService();
        os.loadOrderFile("src/test/resources/ordersTestValidContent.txt", bs);
        CalculationService cs = new CalculationService();
        ReportService rs = cs.calculateCost(os,bs);
        rs.printReports();
        assertEquals(3,rs.getReports().size());
        assertEquals(13027.5,rs.getReports().stream().mapToDouble(Report::getTotalCost).sum());
    }
}
