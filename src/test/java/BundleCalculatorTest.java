import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BundleCalculatorTest {
    private BundleCalculator bc;

    @BeforeEach
    public void setup() {
        bc = new BundleCalculator();
    }

    @Test
    @DisplayName("Test bundle calculator")
    public void testMain() throws DataFormatException, DataAccessException {
        bc.getBs().updatePriceListFromFile("src/test/resources/bundlesTestValidContent.txt");
        bc.getOs().loadOrderFile("src/test/resources/ordersTestValidContent.txt", bc.getBs());
        bc.setRs(bc.getCs().calculateCost(bc.getOs(), bc.getBs()));
        bc.getRs().printReports();
        assertEquals(3,bc.getRs().getReports().size());
        assertEquals(13027.5,bc.getRs().getReports().stream().mapToDouble(Report::getTotalCost).sum());
    }
}
