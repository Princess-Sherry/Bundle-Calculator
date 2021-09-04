package service;

import entity.AudioPriceList;
import entity.ImagePriceList;
import entity.VideoPriceList;
import entity.BasePriceList;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * This PriceListService Class is to handle the price lists from social media influencers.
 */
@Getter
public class PriceListService {
    private final static Logger LOGGER = Logger.getLogger(PriceListService.class.getName());
    private ImagePriceList imagePriceList = new ImagePriceList();
    private AudioPriceList audioPriceList = new AudioPriceList();
    private VideoPriceList videoPriceList = new VideoPriceList();
    private final HashMap<String, BasePriceList> formatPriceListMapping = new HashMap<>();

    private void initialiseFormatPriceListMap() {
        PriceListService ps = new PriceListService();
        formatPriceListMapping.put("IMG", ps.imagePriceList);
        formatPriceListMapping.put("FLAC", ps.audioPriceList);
        formatPriceListMapping.put("VID", ps.videoPriceList);
    }

    private void updatePriceList(BasePriceList priceList, String format, String formatCode, String[] bundles){
        priceList.setFormat(format);
        priceList.setFormatCode(formatCode);
        for (int i = 0; i < bundles.length - 1; i = i + 2) {
            int amount = Integer.parseInt(bundles[i]);
            double price = Double.parseDouble(bundles[i + 1]);
            priceList.addBundle(amount, price);
        }
    }

    /**
     * Import and store the price lists from file
     * @param path price list file path
     */
    public void updatePriceListFromFile(String path){
        LOGGER.info("Start reading price list from file " + path);
        initialiseFormatPriceListMap();
        BufferedReader inputPriceFile;
        try {
            inputPriceFile = new BufferedReader(new FileReader(path));
            inputPriceFile.readLine();
            inputPriceFile.readLine();
            String line;
            while ((line = inputPriceFile.readLine()) != null) {
                String[] lineSplit = line.split("\\|");
                String format = lineSplit[0].trim().toUpperCase();
                String formatCode = lineSplit[1].trim().toUpperCase();
                String[] bundles = lineSplit[2].trim().split("[^0-9.']+");
                this.formatPriceListMapping.forEach((formatKey,priceListValue) -> {
                    if (formatKey.equals(formatCode)) {
                        updatePriceList(priceListValue, format, formatCode, bundles);
                    }
                } );
            }
            inputPriceFile.close();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        } finally {
            LOGGER.info("Finish reading price list from file " + path);
        }
    }
}
