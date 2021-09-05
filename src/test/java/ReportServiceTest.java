import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReportServiceTest {
    ReportService rs;

    @BeforeEach
    void setup() {
        rs = new ReportService();
    }

    @Test
    @DisplayName("Test calculate and print cost method")
    void testCalculateAndPrintCost() {
        BundleService ps = new BundleService();
        ps.updatePriceListFromFile("src/test/resources/bundlesTestValidContent.txt");
        OrderService os = new OrderService();
        os.loadOrderFile("src/test/resources/ordersTestValidContent.txt");
        rs.calculateAndPrintCost(os,ps);
        assertEquals(1,rs.getReports().size());
        assertTrue(rs.getReports().toString().contains("IMG"));
        assertFalse(rs.getReports().toString().contains("FLAC"));
        assertFalse(rs.getReports().toString().contains("VID"));
        assertFalse(rs.getReports().toString().contains("Img"));
    }
}
