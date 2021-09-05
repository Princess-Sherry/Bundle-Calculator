import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This OrderService Class is to handle customer's orders input
 */
@Getter
public class OrderService {
    private final LinkedHashMap<String, Integer> orders = new LinkedHashMap<>();

    /**
     * Import and store the orders input from file
     * @param path orders file path
     */
    public void loadOrderFile(String path, BundleService bs) throws DataFormatException, DataAccessException {
        BufferedReader input;
        try {
            String line;
            input = new BufferedReader(new FileReader(path));
            while ((line = input.readLine()) != null && !line.equals("")) {
                String[] lineSplit = line.split(" ");
                if (lineSplit.length != 2) { throw new DataFormatException(); }
                if (!bs.getBundles().stream().anyMatch(bundle -> bundle.getFormatCode().equals(lineSplit[1]))) {
                    List<String> validFormats = bs.getBundles().stream().map(bundle -> bundle.getFormatCode()).collect(Collectors.toList());
                    throw new DataFormatException("Format " + lineSplit[1] + " is not supported. We only support the following formats: " + validFormats.toString());
                }
                this.orders.put(lineSplit[1].toUpperCase(), Integer.parseInt(lineSplit[0]));
            }
            input.close();
        } catch (FileNotFoundException e) {
            throw new DataAccessException(e.getMessage());
        } catch (IOException e) {
            throw new DataAccessException(e.getMessage());
        } catch (DataFormatException e) {
            throw new DataFormatException(e.getMessage());
        } catch (NumberFormatException e) {
            throw new DataFormatException(e.getMessage() + ": invalid number format. Order amount must be integer.");
        }
    }
}
