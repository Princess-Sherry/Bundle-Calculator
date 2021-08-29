package entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BasePriceListTest {
    BasePriceList basePriceList;

    @BeforeEach
    void setUp(){
        basePriceList = new BasePriceList();
    }

    @Test
    @DisplayName("Test add bundle")
    void testAddBundle(){
        basePriceList.addBundle(2,500);
        assertEquals(1, basePriceList.getBundles().size());
        assertEquals(500,basePriceList.getBundles().get(2));
    }
}
