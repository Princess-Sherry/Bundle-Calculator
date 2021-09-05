import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * This OrderService Class is to handle customer's orders input
 */
@Getter
public class OrderService {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private final LinkedHashMap<String, Integer> orders = new LinkedHashMap<>();

    /**
     * Import and store the orders input from file
     * @param path orders file path
     */
    public void loadOrderFile(String path, BundleService bs)  {
        LOGGER.info("Start reading orders from file " + path);
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
            LOGGER.severe(e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            System.exit(0);
        } catch (DataFormatException e) {
            LOGGER.severe(e.getMessage());
            System.exit(0);
        } catch (NumberFormatException e) {
            LOGGER.severe(e.getMessage() + ": invalid number format. Order amount must be integer.");
            System.exit(0);
        } finally {
            LOGGER.info("Finish reading orders from file " + path);
        }
    }
}
