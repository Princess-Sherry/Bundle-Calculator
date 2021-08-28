package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter @Getter
public class BasePriceList {
    private HashMap<Integer, Double> bundles = new HashMap<Integer, Double>();
    private String format;
    private String formatCode;

    public void addBundle(int amount, double price){
        bundles.put(amount, price);
    }
}
