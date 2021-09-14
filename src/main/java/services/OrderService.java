package services;

import entities.OrderItem;
import exceptions.DataFormatException;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * This services.OrderService Class is to handle customer's order input
 */
@Getter
public class OrderService {
    /**
     * Import the order input from file from customer
     *
     * @param path order file path
     * @return order
     */
    public List<OrderItem> loadOrderFile(String path) throws DataFormatException, IOException, NumberFormatException {
        List<OrderItem> order = new LinkedList<>();

        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = input.readLine()) != null && !line.equals("")) {
                String[] lineSplit = line.split(" ");
                if (lineSplit.length != 2) {
                    throw new DataFormatException("Orders file format must be in two columns. Please check your file.");
                }
                order.add(new OrderItem(Integer.parseInt(lineSplit[0]), lineSplit[1].toUpperCase()));
            }
        }

        return order;
    }
}
