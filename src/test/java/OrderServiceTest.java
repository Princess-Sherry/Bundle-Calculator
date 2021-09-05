import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest {
    OrderService os;

    @BeforeEach
    void setup() {
        os = new OrderService();
    }

    @Test
    @DisplayName("Test order file with valid content")
    void testLoadOrderFileWithValidPathAndContent() {
        String validPath = "src/test/resources/ordersTestValidContent.txt";
        os.loadOrderFile(validPath);
        assertEquals(1, os.getOrders().size());
    }

    @Test
    @DisplayName("Test order file with invalid content")
    void testLoadOrderFileWithInvalidContent() {
        String invalidContentPath = "src/test/resources/ordersTestInvalidContentWithOneColumn.txt";
        assertThrows(ArrayIndexOutOfBoundsException.class, ()-> os.loadOrderFile(invalidContentPath));
    }
}
