import entities.Bundle;
import exceptions.DataFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.BundleService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BundleServiceTest {
    private final static Logger LOGGER = Logger.getLogger(BundleService.class.getName());
    private BundleService bundleService;

    @BeforeEach
    public void setup() {
        bundleService = new BundleService();
    }

    @Test
    @DisplayName("Test import bundle file with valid content")
    public void testUpdatePriceListFromFileWithValidContent() throws DataFormatException, IOException {
        String validPath = "src/test/resources/bundlesTestValidContent.txt";
        List<Bundle> bundles = bundleService.loadBundleFile(validPath);
        assertEquals(3, bundles.size());
    }

    @Test
    @DisplayName("Test import bundle file with invalid file path")
    public void testUpdatePriceListFromFileWithInvalidFilePath() {
        IOException thrown = assertThrows(
                IOException.class,
                () -> {
                    String invalidPath = "sr/test/resources/bundlesTestValidContent.txt";
                    bundleService.loadBundleFile(invalidPath);
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
                    bundleService.loadBundleFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("Header"));
    }

    @Test
    @DisplayName("Test import bundle file with improper alphabet in price")
    public void testUpdatePriceListFromFileWithAlphabetInPrice() {
        DataFormatException thrown = assertThrows(
                DataFormatException.class,
                () -> {
                    String invalidPath = "src/test/resources/bundlesTestWithAlphabetInPrice.txt";
                    bundleService.loadBundleFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("not in the correct format"));
    }

    @Test
    @DisplayName("Test import bundle file with invalid number format")
    public void testUpdatePriceListFromFileWithInvalidNumberFormat() {
        NumberFormatException thrown = assertThrows(
                NumberFormatException.class,
                () -> {
                    String invalidPath = "src/test/resources/bundlesTestWithNonIntegerForBundleVolume.txt";
                    bundleService.loadBundleFile(invalidPath);
                }
        );
        LOGGER.severe(thrown.getMessage());
        assertTrue(thrown.getMessage().contains("For input"));
    }
}
