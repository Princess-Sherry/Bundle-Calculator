package entity.input.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BasePriceListTest {
    BasePriceList basePriceList;

    @BeforeEach
    void setUp(){
        basePriceList = new BasePriceList();
    }

    @Test
    @DisplayName("Test add bundle")
    void testAddBundle(){
        Bundle testBundle = new Bundle(1, 2.5);
        basePriceList.addBundle(testBundle);
        assertEquals(1, basePriceList.getBundles().size());
    }
}
