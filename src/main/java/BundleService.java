import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * This BundleService Class is to handle the bundle prices input from social media influencers.
 */
@Getter
public class BundleService {
    private final static Logger LOGGER = Logger.getLogger(BundleService.class.getName());
    private ArrayList<Bundle> bundles = new ArrayList<>();

    /**
     * Import and store the bundle prices from file
     * @param path bundle file path
     */
    public void updatePriceListFromFile(String path) {
        LOGGER.info("Start reading price list from file " + path);
        BufferedReader inputPriceFile;
        try {
            inputPriceFile = new BufferedReader(new FileReader(path));
            String firstLine = inputPriceFile.readLine();
            String secondLine = inputPriceFile.readLine();
            if (firstLine.matches(".*\\d.*") || secondLine.matches(".*\\d.*")) {
                throw new DataFormatException();
            }
            String line;
            while ((line = inputPriceFile.readLine()) != null && !line.equals("") ) {
                String[] lineSplit = line.split("\\|");
                if (lineSplit.length != 3) { throw new DataFormatException(); }

                String formatCode = lineSplit[1].trim().toUpperCase();

                if (lineSplit[2].trim().matches(".*[a-zA-Z]+.*")) { throw new DataFormatException(); }
                String[] bundles = lineSplit[2].trim().split("[^0-9.']+");
                if (bundles.length % 2 != 0) { throw new DataFormatException(); }

                ArrayList<BundleItem> bundleItems = new ArrayList<>();
                for (int i = 0; i < bundles.length - 1; i = i + 2) {
                    int bundleVolume = Integer.parseInt(bundles[i]);
                    double price = Double.parseDouble(bundles[i + 1]);
                    bundleItems.add(new BundleItem(bundleVolume, price));
                }
                this.bundles.add(new Bundle(formatCode, bundleItems));
            }
            inputPriceFile.close();
        } catch (FileNotFoundException e) {
            LOGGER.severe(e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            System.exit(0);
        } catch (DataFormatException e) {
            LOGGER.severe(e.getMessage());
            System.exit(0);
        } catch (NumberFormatException e) {
            LOGGER.severe(e.getMessage() + ": invalid number format. Please refer to README for correct format.");
            System.exit(0);
        } finally {
            LOGGER.info("Finish reading price list from file " + path);
        }
    }
}
