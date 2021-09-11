package services;

import entities.Bundle;
import entities.BundleItem;
import exceptions.DataAccessException;
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
    public List<Bundle> loadBundleFile(String path) throws DataFormatException, DataAccessException {
        List<Bundle> bundles = new ArrayList<>();

        BufferedReader inputPriceFile;
        try {
            inputPriceFile = new BufferedReader(new FileReader(path));
            String firstLine = inputPriceFile.readLine();
            String secondLine = inputPriceFile.readLine();
            if (firstLine.matches(".*\\d.*") || secondLine.matches(".*\\d.*")) {
                throw new DataFormatException();
            }
            String line;
            while ((line = inputPriceFile.readLine()) != null && !line.equals("")) {
                String[] lineSplit = line.split("\\|");
                if (lineSplit.length != 3) {
                    throw new DataFormatException();
                }

                String formatCode = lineSplit[1].trim().toUpperCase();

                if (lineSplit[2].trim().matches(".*[a-zA-Z]+.*")) {
                    throw new DataFormatException();
                }
                String[] bundlesSplit = lineSplit[2].trim().split("[^0-9.']+");
                if (bundlesSplit.length % 2 != 0) {
                    throw new DataFormatException();
                }

                ArrayList<BundleItem> bundleItems = new ArrayList<>();
                for (int i = 0; i < bundlesSplit.length - 1; i = i + 2) {
                    int bundleVolume = Integer.parseInt(bundlesSplit[i]);
                    double price = Double.parseDouble(bundlesSplit[i + 1]);
                    bundleItems.add(new BundleItem(bundleVolume, price));
                }
                bundles.add(new Bundle(formatCode, bundleItems));
            }
            inputPriceFile.close();
        } catch (IOException e) {
            throw new DataAccessException(e.getMessage());
        } catch (DataFormatException e) {
            throw new DataFormatException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new DataFormatException(e.getMessage() + ": invalid number format. Please refer to README for correct format.");
        }

        return bundles;
    }
}
