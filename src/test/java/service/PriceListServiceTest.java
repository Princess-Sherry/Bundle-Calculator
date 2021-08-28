package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;;

public class PriceListServiceTest {
    PriceListService ps;

    @BeforeEach
    void setup() {
        ps = new PriceListService();
    }

    @Test
    @DisplayName("Test priceList file")
    void testUpdatePriceListFromFile() {
        String invalidPath = "src/test/resources/priceListTest.txt";
        ps.updatePriceListFromFile(invalidPath);
        assertEquals(3, ps.getFormatPriceListMapping().size());
    }
}
