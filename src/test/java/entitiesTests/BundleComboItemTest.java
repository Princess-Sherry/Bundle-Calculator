package entitiesTests;

import entities.BundleComboItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BundleComboItemTest {
    private BundleComboItem bundleComboItem;

    @BeforeEach
    public void setup() {
        bundleComboItem = new BundleComboItem();
    }

    @Test
    @DisplayName("Test getSubTotal method")
    public void testGetSubTotal() {
        bundleComboItem.setNumberOfBundles(2);
        bundleComboItem.setPrice(200.2);
        assertEquals(400.4, bundleComboItem.getSubTotal());
    }
}
