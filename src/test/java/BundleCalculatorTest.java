import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BundleCalculatorTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Test bundle calculator main method")
    public void testMain() {
        String bundleFilePath = "src/test/resources/bundlesTestValidContent.txt";
        String orderFilePath = "src/test/resources/orderTestValidContent.txt";
        BundleCalculator.main(new String[]{bundleFilePath, orderFilePath});
        assertTrue(outputStreamCaptor.toString().contains("2850.0"));
        assertTrue(outputStreamCaptor.toString().contains("5017.5"));
        assertTrue(outputStreamCaptor.toString().contains("5160.0"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
