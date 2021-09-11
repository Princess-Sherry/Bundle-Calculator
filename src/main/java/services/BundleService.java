package services;

import entities.Bundle;
import entities.BundleItem;
import exceptions.DataFormatException;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This services.BundleService Class is to handle the bundle prices input from social media influencers.
 */
@Getter
public class BundleService {
    /**
     * Import the bundle prices from file
     *
     * @param path bundle file path
     * @return bundle prices lists
     */
    public List<Bundle> loadBundleFile(String path) throws DataFormatException, IOException, NumberFormatException {
        List<Bundle> bundles = new ArrayList<>();

        try (BufferedReader inputPriceFile = new BufferedReader(new FileReader(path))) {
            String firstLine = inputPriceFile.readLine();
            String secondLine = inputPriceFile.readLine();
            if (firstLine.matches(".*\\d.*") || secondLine.matches(".*\\d.*")) {
                throw new DataFormatException("Header is needed. Please check your file format.");
            }

            String line;
            while ((line = inputPriceFile.readLine()) != null && !line.equals("")) {
                String[] lineSplit = line.split("\\|");
                if (lineSplit.length != 3) {
                    throw new DataFormatException("Bundle file format must be in three columns. Please check your file.");
                }

                String formatCode = lineSplit[1].trim().toUpperCase();

                if (lineSplit[2].trim().matches(".*[a-zA-Z]+.*")) {
                    throw new DataFormatException("Bundles are not in the correct format. Please check your file.");
                }
                String[] bundlesSplit = lineSplit[2].trim().split("[^0-9.']+");
                if (bundlesSplit.length % 2 != 0) {
                    throw new DataFormatException("Bundles are not in valid volume-price pair format. Please check your file.");
                }

                ArrayList<BundleItem> bundleItems = new ArrayList<>();
                for (int i = 0; i < bundlesSplit.length - 1; i = i + 2) {
                    int bundleVolume = Integer.parseInt(bundlesSplit[i]);
                    double price = Double.parseDouble(bundlesSplit[i + 1]);
                    bundleItems.add(new BundleItem(bundleVolume, price));
                }
                bundles.add(new Bundle(formatCode, bundleItems));
            }
        }

        return bundles;
    }
}
