package service;

import entity.*;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This PriceListService Class is to handle the price lists from social media influencers.
 */
@Getter
public class BundleService {
    private final static Logger LOGGER = Logger.getLogger(BundleService.class.getName());

    private ArrayList<Bundle> bundles = new ArrayList<>();

    /**
     * Import and store the bundle prices from file
     * @param path price list file path
     */
    public void updatePriceListFromFile(String path){
        LOGGER.info("Start reading price list from file " + path);
        BufferedReader inputPriceFile;
        try {
            inputPriceFile = new BufferedReader(new FileReader(path));
            inputPriceFile.readLine();
            inputPriceFile.readLine();
            String line;
            while ((line = inputPriceFile.readLine()) != null) {
                String[] lineSplit = line.split("\\|");
                String formatCode = lineSplit[1].trim().toUpperCase();
                String[] bundles = lineSplit[2].trim().split("[^0-9.']+");
                ArrayList<BundleItem> bundleItems = new ArrayList<>();
                for (int i = 0; i < bundles.length - 1; i = i + 2) {
                    int bundleVolume = Integer.parseInt(bundles[i]);
                    double price = Double.parseDouble(bundles[i + 1]);
                    bundleItems.add(new BundleItem(bundleVolume, price));
                }
                this.bundles.add(new Bundle(formatCode, bundleItems));
            }
            inputPriceFile.close();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            LOGGER.info("Finish reading price list from file " + path);
        }
    }
}
