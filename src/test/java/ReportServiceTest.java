import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportServiceTest {
    private final static Logger LOGGER = Logger.getLogger(BundleService.class.getName());
    private ReportService rs;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        rs = new ReportService();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Test print reports method")
    public void testPrintReports() {
        ArrayList<Breakdown> breakdowns = new ArrayList<>();
        breakdowns.add(new Breakdown(2,3,100));
        breakdowns.add(new Breakdown(3,6,400));
        rs.getReports().add(new Report(10, "IMG", 500, breakdowns));
        rs.printReports();
        assertEquals("10 IMG $500.0\n" + " 2 x 3 $100.0\n" + " 3 x 6 $400.0\n", outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
