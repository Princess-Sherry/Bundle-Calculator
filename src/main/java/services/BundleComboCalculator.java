package services;

import java.util.*;

/**
 * A calculator to get the bundle combination to achieve minimal bundles
 */
public class BundleComboCalculator {
    /**
     * Algorithm to achieve minimal bundles
     *
     * @return bundle combination
     */
    public HashMap<Integer, Integer> getCombo(int targetAmount, List<Integer> amounts) {
        int BIGINT = 9999999;
        if (targetAmount < amounts.get(0)) {
            targetAmount = amounts.get(0);
        }

        int maxAmount = Collections.max(amounts);

        int[] minStorage = new int[targetAmount + maxAmount];
        int[] minPath = new int[targetAmount + maxAmount];

        for (int i = 1; i < minStorage.length; i++) {
            minStorage[i] = BIGINT;
        }

        minStorage[0] = 0;
        for (Integer amount : amounts) {
            for (int j = amount; j < minStorage.length; j++) {
                if (minStorage[j] >= minStorage[j - amount] + 1) {
                    minStorage[j] = minStorage[j - amount] + 1;
                    minPath[j] = amount;
                }
            }
        }

        HashMap<Integer, Integer> combo = new LinkedHashMap<>();
        int s = targetAmount;
        while (s != 0 && s < minStorage.length) {
            if (minStorage[s] == BIGINT) {
                s++;
            } else {
                if (!combo.containsKey(minPath[s])) {
                    combo.put(minPath[s], 1);
                } else {
                    combo.put(minPath[s], combo.get(minPath[s]) + 1);
                }
                s -= minPath[s];
            }
        }
        return combo;
    }
}
