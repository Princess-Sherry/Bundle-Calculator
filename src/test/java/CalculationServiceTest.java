import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationServiceTest {
    private CalculationService cs;

    @BeforeEach
    public void setup() {
        cs = new CalculationService();
    }

    @Test
    @DisplayName("Test calculate cost method")
    public void testCalculateCost() throws DataFormatException, DataAccessException {
        BundleService bs = new BundleService();
        bs.updatePriceListFromFile("src/test/resources/bundlesTestValidContent.txt");
        OrderService os = new OrderService();
        os.loadOrderFile("src/test/resources/ordersTestValidContent.txt", bs);
        cs.calculateCost(os, bs);
        assertEquals(3, cs.getRs().getReports().size());
    }
}
