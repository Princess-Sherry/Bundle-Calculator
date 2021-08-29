package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;

public class BundleComboCalculatorTest {
    BundleComboCalculator bundleComboCalculator;

    @BeforeEach
    void setUp(){
        bundleComboCalculator = new BundleComboCalculator(0,null);
    }

    @Test
    @DisplayName("Test algorithm to get minimal bundles")
    void testBundleComboCalculator(){
        bundleComboCalculator.setTargetAmount(13);
        bundleComboCalculator.setAmounts(Arrays.asList(new Integer[]{3, 5, 9}));
        HashMap<Integer,Integer> result = bundleComboCalculator.getBundleCombination();
        assertEquals(1,result.get(3));
        assertEquals(2,result.get(5));
    }
}
