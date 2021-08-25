package service;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

@Getter
public class OrderService {
    private LinkedHashMap<String, Integer> orders = new LinkedHashMap<String, Integer>();

    public void loadOrderFile(String path) throws IOException {
        BufferedReader input = null;
        try {
            String line;
            input = new BufferedReader(new FileReader(path));
            while ((line = input.readLine()) != null) {
                String[] lineSplit = line.split(" ");
                this.orders.put(lineSplit[1], Integer.parseInt(lineSplit[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
    }
}
