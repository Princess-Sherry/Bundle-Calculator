import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {
    private final static Logger LOGGER = Logger.getLogger(BundleService.class.getName());
    private OrderService os;

    @BeforeEach
    public void setup() { os = new OrderService(); }

    @Test
    @DisplayName("Test order file with valid content")
    public void testLoadOrderFileWithValidPathAndContent() throws DataFormatException, DataAccessException {
        BundleService bs = new BundleService();
        bs.updatePriceListFromFile("src/test/resources/bundlesTestValidContent.txt");
        String validPath = "src/test/resources/ordersTestValidContent.txt";
        os.loadOrderFile(validPath, bs);
        assertEquals(3, os.getOrders().size());
    }

    @Test
    @DisplayName("Test order file with invalid number format")
    public void testLoadOrderFileWithInvalidNumberFormat() {
        DataFormatException thrown = assertThrows(
                DataFormatException.class,
                () -> {
                    BundleService bs = new BundleService();
                    bs.updatePriceListFromFile("src/test/resources/bundlesTestValidContent.txt");
                    String invalidPath = "src/test/resources/ordersTestWithNonIntegerAmount.txt";
                    os.loadOrderFile(invalidPath, bs);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("invalid number format"));
    }

    @Test
    @DisplayName("Test order file with unsupported format")
    public void testLoadOrderFileWithUnsupportedFormat() {
        DataFormatException thrown = assertThrows(
                DataFormatException.class,
                () -> {
                    BundleService bs = new BundleService();
                    bs.updatePriceListFromFile("src/test/resources/bundlesTestValidContent.txt");
                    String invalidPath = "src/test/resources/ordersTestWithUnsupportedFormat.txt";
                    os.loadOrderFile(invalidPath, bs);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("not supported"));
    }

    @Test
    @DisplayName("Test order file not in two columns")
    public void testLoadOrderFileNotInTwoColumns() {
        DataFormatException thrown = assertThrows(
                DataFormatException.class,
                () -> {
                    BundleService bs = new BundleService();
                    bs.updatePriceListFromFile("src/test/resources/bundlesTestValidContent.txt");
                    String invalidPath = "src/test/resources/ordersTestWithOneColumn.txt";
                    os.loadOrderFile(invalidPath, bs);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("File data is not in the correct format"));
    }

    @Test
    @DisplayName("Test order file with invalid path")
    public void testLoadOrderFileWithInvalidPath() {
        DataAccessException thrown = assertThrows(
                DataAccessException.class,
                () -> {
                    BundleService bs = new BundleService();
                    bs.updatePriceListFromFile("src/test/resources/bundlesTestValidContent.txt");
                    String invalidPath = "sr/test/resources/ordersTestWithOneColumn.txt";
                    os.loadOrderFile(invalidPath, bs);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("No such file"));
    }
}
