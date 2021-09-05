import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;

public class BundleComboCalculatorTest {
    private BundleComboCalculator bundleComboCalculator;

    @BeforeEach
    public void setUp(){
        bundleComboCalculator = new BundleComboCalculator(0,null);
    }

    @Test
    @DisplayName("Test algorithm with achievable regular bundle combo")
    public void testBundleComboCalculatorWithAchievableRegularBundleCombo() {
        bundleComboCalculator.setTargetAmount(13);
        bundleComboCalculator.setAmounts(Arrays.asList(new Integer[]{3, 5, 9}));
        HashMap<Integer,Integer> result = bundleComboCalculator.getBundleCombination();
        assertEquals(1,result.get(3));
        assertEquals(2,result.get(5));
    }

    @Test
    @DisplayName("Test algorithm with unachievable regular bundle combo")
    public void testBundleComboCalculatorWithUnachievableRegularBundleCombo() {
        bundleComboCalculator.setTargetAmount(7);
        bundleComboCalculator.setAmounts(Arrays.asList(new Integer[]{3, 5, 9}));
        HashMap<Integer,Integer> result = bundleComboCalculator.getBundleCombination();
        assertEquals(1,result.get(3));
        assertEquals(1,result.get(5));
    }

    @Test
    @DisplayName("Test algorithm with target amount less than the minimum bundle size")
    public void testBundleComboCalculatorWithTargetAmountLessThanMinimumBundleSize() {
        bundleComboCalculator.setTargetAmount(4);
        bundleComboCalculator.setAmounts(Arrays.asList(new Integer[]{5, 10}));
        HashMap<Integer,Integer> result = bundleComboCalculator.getBundleCombination();
        assertEquals(1,result.get(5));
    }
}
