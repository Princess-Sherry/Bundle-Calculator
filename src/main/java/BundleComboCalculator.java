import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * A calculator to get the bundle combinations to achieve minimal bundles
 */
@AllArgsConstructor
@Setter
public class BundleComboCalculator {
    private int targetAmount;
    private List<Integer> amounts;

    /**
     * Algorithm to achieve minimal bundles
     * @return bundle combinations
     */
    public HashMap<Integer, Integer> getBundleCombination() {
        int BIGINT = 9999999;
        if (targetAmount < amounts.get(0)) {
            this.targetAmount = amounts.get(0);
        }

        int maxAmount =  Collections.max(amounts);

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

        HashMap<Integer, Integer> combos = new HashMap<>();
        int s = targetAmount;
        while (s != 0 && s < minStorage.length) {
            if(minStorage[s] == BIGINT){
                s++;
            } else {
                if (!combos.containsKey(minPath[s])) {
                    combos.put(minPath[s], 1);
                } else {
                    combos.put(minPath[s], combos.get(minPath[s]) + 1);
                }
                s -= minPath[s];
            }
        }
        return combos;
    }
}
