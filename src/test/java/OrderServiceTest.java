import entities.Order;
import exceptions.DataFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.BundleService;
import services.OrderService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {
    private final static Logger LOGGER = Logger.getLogger(BundleService.class.getName());
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        orderService = new OrderService();
    }

    @Test
    @DisplayName("Test order file with valid content")
    public void testLoadOrderFileWithValidPathAndContent() throws DataFormatException, IOException {
        String validPath = "src/test/resources/ordersTestValidContent.txt";
        List<Order> orders = orderService.loadOrderFile(validPath);
        assertEquals(3, orders.size());
    }

    @Test
    @DisplayName("Test order file with invalid number format")
    public void testLoadOrderFileWithInvalidNumberFormat() {
        NumberFormatException thrown = assertThrows(
                NumberFormatException.class,
                () -> {
                    String invalidPath = "src/test/resources/ordersTestWithNonIntegerAmount.txt";
                    orderService.loadOrderFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("For input"));
    }

    @Test
    @DisplayName("Test order file not in two columns")
    public void testLoadOrderFileNotInTwoColumns() {
        DataFormatException thrown = assertThrows(
                DataFormatException.class,
                () -> {
                    String invalidPath = "src/test/resources/ordersTestWithOneColumn.txt";
                    orderService.loadOrderFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("two columns"));
    }

    @Test
    @DisplayName("Test order file with invalid path")
    public void testLoadOrderFileWithInvalidPath() {
        IOException thrown = assertThrows(
                IOException.class,
                () -> {
                    String invalidPath = "sr/test/resources/ordersTestWithOneColumn.txt";
                    orderService.loadOrderFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("No such file"));
    }
}
