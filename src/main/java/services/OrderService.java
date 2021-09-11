package services;

import entities.Order;
import exceptions.DataFormatException;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * This services.OrderService Class is to handle customer's orders input
 */
@Getter
public class OrderService {
    /**
     * Import the orders input from file from customer
     *
     * @param path orders file path
     * @return orders
     */
    public List<Order> loadOrderFile(String path) throws DataFormatException, IOException, NumberFormatException {
        List<Order> orders = new LinkedList<>();

        try (BufferedReader input = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = input.readLine()) != null && !line.equals("")) {
                String[] lineSplit = line.split(" ");
                if (lineSplit.length != 2) {
                    throw new DataFormatException("Orders file format must be in two columns. Please check your file.");
                }
                orders.add(new Order(Integer.parseInt(lineSplit[0]), lineSplit[1].toUpperCase()));
            }
        }

        return orders;
    }
}
