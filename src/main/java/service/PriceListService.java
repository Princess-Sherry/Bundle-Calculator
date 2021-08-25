package service;

import entity.input.AudioPriceList;
import entity.input.ImagePriceList;
import entity.input.VideoPriceList;
import entity.input.base.BasePriceList;
import entity.input.base.Bundle;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Getter
public class PriceListService {
    private final static Logger LOGGER = Logger.getLogger(PriceListService.class.getName());
    private ImagePriceList imagePriceList = new ImagePriceList();
    private AudioPriceList audioPriceList = new AudioPriceList();
    private VideoPriceList videoPriceList = new VideoPriceList();
    private HashMap<String, BasePriceList> formatPriceListMapping = new HashMap<>();

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
            priceList.addBundle(new Bundle(amount, price));
        }
    }

    public void updatePriceListFromFile(String path) throws IOException {
        initialiseFormatPriceListMap();
        BufferedReader inputPriceFile = null;
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
                for (Map.Entry<String, BasePriceList> e: this.formatPriceListMapping.entrySet()) {
                    if (e.getKey().equals(formatCode)) {
                        updatePriceList(e.getValue(), format, formatCode, bundles);
                    }
                }
            }
        } catch (IOException e) {
//            logger.log();
            e.getMessage();
        } finally {
            inputPriceFile.close();
        }
    }
}
