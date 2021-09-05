import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BundleServiceTest {
    private final static Logger LOGGER = Logger.getLogger(BundleService.class.getName());
    private BundleService bs;

    @BeforeEach
    public void setup() {
        bs = new BundleService();
    }

    @Test
    @DisplayName("Test import bundle file with valid content")
    public void testUpdatePriceListFromFileWithValidContent() throws DataFormatException, DataAccessException {
        String validPath = "src/test/resources/bundlesTestValidContent.txt";
        bs.updatePriceListFromFile(validPath);
        assertEquals(3, bs.getBundles().size());
    }

    @Test
    @DisplayName("Test import bundle file with invalid file path")
    public void testUpdatePriceListFromFileWithInvalidFilePath() {
        DataAccessException thrown = assertThrows(
                DataAccessException.class,
                () -> {
                    String invalidPath = "sr/test/resources/bundlesTestValidContent.txt";
                    bs.updatePriceListFromFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("No such file"));
    }

    @Test
    @DisplayName("Test import bundle file without proper header in content")
    public void testUpdatePriceListFromFileWithoutHeader() {
        DataFormatException thrown = assertThrows(
                DataFormatException.class,
                () -> {
                    String invalidPath = "src/test/resources/bundlesTestWithoutHeader.txt";
                    bs.updatePriceListFromFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("File data is not in the correct format"));
    }

    @Test
    @DisplayName("Test import bundle file with improper alphabet in price")
    public void testUpdatePriceListFromFileWithAlphabetInPrice() {
        DataFormatException thrown = assertThrows(
                DataFormatException.class,
                () -> {
                    String invalidPath = "src/test/resources/bundlesTestWithAlphabetInPrice.txt";
                    bs.updatePriceListFromFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("File data is not in the correct format"));
    }

    @Test
    @DisplayName("Test import bundle file with invalid number format")
    public void testUpdatePriceListFromFileWithInvalidNumberFormat() {
        DataFormatException thrown = assertThrows(
                DataFormatException.class,
                () -> {
                    String invalidPath = "src/test/resources/bundlesTestWithNonIntegerForBundleVolume.txt";
                    bs.updatePriceListFromFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("invalid number format"));
    }
}
