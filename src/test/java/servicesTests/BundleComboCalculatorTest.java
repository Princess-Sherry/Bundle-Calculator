package servicesTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.BundleComboCalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;

public class BundleComboCalculatorTest {
    private BundleComboCalculator bundleComboCalculator;

    @BeforeEach
    public void setUp() {
        bundleComboCalculator = new BundleComboCalculator();
    }

    @Test
    @DisplayName("Test algorithm with achievable regular bundle combo")
    public void testBundleComboCalculatorWithAchievableRegularBundleCombo() {
        HashMap<Integer, Integer> combo = bundleComboCalculator.getCombo(13, Arrays.asList(3, 5, 9));
        assertEquals(1, combo.get(3));
        assertEquals(2, combo.get(5));
    }

    @Test
    @DisplayName("Test algorithm with unachievable regular bundle combo")
    public void testBundleComboCalculatorWithUnachievableRegularBundleCombo() {
        HashMap<Integer, Integer> combo = bundleComboCalculator.getCombo(7, Arrays.asList(3, 5, 9));
        assertEquals(1, combo.get(3));
        assertEquals(1, combo.get(5));
    }

    @Test
    @DisplayName("Test algorithm with target amount less than the minimum bundle size")
    public void testBundleComboCalculatorWithTargetAmountLessThanMinimumBundleSize() {
        HashMap<Integer, Integer> combo = bundleComboCalculator.getCombo(4, Arrays.asList(5, 10));
        assertEquals(1, combo.get(5));
    }
}
