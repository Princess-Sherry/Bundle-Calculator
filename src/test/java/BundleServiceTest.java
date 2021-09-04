import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BundleServiceTest {
    BundleService bs;

    @BeforeEach
    void setup() {
        bs = new BundleService();
    }

    @Test
    @DisplayName("Test bundle prices file")
    void testUpdatePriceListFromFile() {
        String invalidPath = "src/test/resources/priceListTest.txt";
        bs.updatePriceListFromFile(invalidPath);
        assertEquals(3, bs.getBundles().size());
    }
}
