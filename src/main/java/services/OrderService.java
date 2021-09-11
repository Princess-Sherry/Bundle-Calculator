package services;

import entities.Order;
import exceptions.DataAccessException;
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
     * @param path orders file path
     * @return orders
     */
    public List<Order> loadOrderFile(String path) throws DataFormatException, DataAccessException {
        List<Order> orders = new LinkedList<>();
        BufferedReader input;
        try {
            String line;
            input = new BufferedReader(new FileReader(path));
            while ((line = input.readLine()) != null && !line.equals("")) {
                String[] lineSplit = line.split(" ");
                if (lineSplit.length != 2) { throw new DataFormatException(); }
                orders.add(new Order(Integer.parseInt(lineSplit[0]), lineSplit[1].toUpperCase()));
            }
            input.close();
        } catch (IOException e) {
            throw new DataAccessException(e.getMessage());
        } catch (DataFormatException e) {
            throw new DataFormatException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new DataFormatException(e.getMessage() + ": invalid number format. Order amount must be integer.");
        }

        return orders;
    }
}
