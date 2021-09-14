package entitiesTests;

import entities.BundleCombo;
import entities.BundleComboItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BundleComboTest {
    private BundleCombo bundleCombo;

    @BeforeEach
    public void setup() {
        bundleCombo = new BundleCombo();
    }

    @Test
    @DisplayName("Test getTotalCost method")
    public void testGetTotalCost() {
        List<BundleComboItem> bundleComboItems = new LinkedList<>();
        bundleComboItems.add(new BundleComboItem(2, 6, 100));
        bundleComboItems.add(new BundleComboItem(3, 5, 200));
        bundleCombo.setBundleComboItems(bundleComboItems);
        assertEquals(800, bundleCombo.getTotalCost());
    }
}
