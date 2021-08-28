package service;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

@Getter
public class OrderService {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private LinkedHashMap<String, Integer> orders = new LinkedHashMap<String, Integer>();

    public void loadOrderFile(String path)  {
        LOGGER.info("Start reading orders from file " + path);
        BufferedReader input = null;
        try {
            String line;
            input = new BufferedReader(new FileReader(path));
            while ((line = input.readLine()) != null) {
                String[] lineSplit = line.split(" ");
                this.orders.put(lineSplit[1], Integer.parseInt(lineSplit[0]));
            }
            input.close();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }
}
