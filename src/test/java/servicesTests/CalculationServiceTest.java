package servicesTests;

import entities.Bundle;
import entities.BundleCombo;
import entities.BundleItem;
import entities.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.BundleComboCalculator;
import services.CalculationService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationServiceTest {
    private CalculationService calculationService;

    @BeforeEach
    public void setup() {
        calculationService = new CalculationService(new BundleComboCalculator());
    }

    @Test
    @DisplayName("Test calculate bundle combos method")
    public void testCalculateBundleCombos() {
        List<OrderItem> order = new LinkedList<>();
        order.add(new OrderItem(4, "IMG"));

        List<Bundle> bundles = new LinkedList<>();
        ArrayList<BundleItem> bundleItems = new ArrayList<>();
        bundleItems.add(new BundleItem(2, 200));
        bundles.add(new Bundle("IMG", bundleItems));

        List<BundleCombo> bundleCombos = calculationService.calculateBundleCombos(order, bundles);

        assertEquals(1, bundleCombos.size());
        assertEquals(400, bundleCombos.get(0).getTotalCost());
    }
}
