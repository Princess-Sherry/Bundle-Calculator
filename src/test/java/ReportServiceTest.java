import entities.BundleCombo;
import entities.BundleComboItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ReportService;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportServiceTest {
    private ReportService reportService;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        reportService = new ReportService();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Test print reports method")
    public void testPrintReports() {
        List<BundleCombo> bundleCombos = new LinkedList<>();
        List<BundleComboItem> bundleComboItems = new LinkedList<>();
        bundleComboItems.add(new BundleComboItem(1, 2, 100));
        bundleCombos.add(new BundleCombo(2, "IMG", bundleComboItems));
        reportService.printReports(bundleCombos);
        assertEquals("2 IMG $100.0\n" + " 1 x 2 $100.0\n", outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
