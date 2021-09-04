package entity;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

/**
 * Base class for all price lists
 */
@Setter @Getter
public class BasePriceList {
    private HashMap<Integer, Double> bundles = new HashMap<>();
    private String format;
    private String formatCode;

    /**
     * Store the bundle-price pair information to the HashMap bundles
     * @param amount bundle amount per unit
     * @param price price per unit
     */
    public void addBundle(int amount, double price){
        bundles.put(amount, price);
    }
}
