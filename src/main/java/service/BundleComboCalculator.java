package service;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Setter
public class BundleComboCalculator {
    private int targetAmount;
    private List<Integer> amounts;

    public HashMap<Integer, Integer> getBundleCombination(){
        int[] minStorage = new int[targetAmount + 1];
        int[] minPath = new int[targetAmount + 1];

        for (int i = 1; i < minStorage.length; i++){
            minStorage[i] = 9999999;
        }
        minStorage[0] = 0;

        for(Integer amount: amounts){
            for (int j = amount; j < minStorage.length; j++){
                if(minStorage[j] > minStorage[j - amount] + 1){
                    minStorage[j] = minStorage[j - amount] + 1;
                    minPath[j] = amount;
                }
            }
        }
        HashMap<Integer, Integer> combos = new HashMap<Integer, Integer>();
        int s = targetAmount;
        while(s != 0){
            if (!combos.containsKey(minPath[s])) {
                combos.put(minPath[s],1);
            } else {
                combos.put(minPath[s], combos.get(minPath[s]) + 1);
            }
            s -= minPath[s];
        }
        return combos;
    }
}
