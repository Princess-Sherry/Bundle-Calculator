import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

public class BundleCalculator {
    private LinkedHashMap<String, Integer> order = new LinkedHashMap<String, Integer>();
    private ArrayList<PriceList> imgPriceList = new ArrayList<PriceList>();
    private ArrayList<PriceList> flacPriceList = new ArrayList<PriceList>();
    private ArrayList<PriceList> vidPriceList = new ArrayList<PriceList>();

    public static void main(String[] args) throws IOException {
        BundleCalculator bc = new BundleCalculator();
        bc.importPriceList("src/priceList.txt");
        bc.importOrderInput("src/input.txt");
        bc.calculateCost(bc.order.get("IMG"), bc.imgPriceList);
        bc.calculateCost(bc.order.get("FLAC"), bc.flacPriceList);
        bc.calculateCost(bc.order.get("VID"), bc.vidPriceList);
    }

    private void calculateCost(int orderBundles, ArrayList<PriceList> priceList) {
        int unassignedBundles = orderBundles;
        ArrayList<Breakdown> breakdowns = new ArrayList<Breakdown>();
        for (int k = 0; k < priceList.size() && unassignedBundles != 0; k++) {
            breakdowns.clear();
            unassignedBundles = orderBundles;
            for (int i = k; i < priceList.size() && unassignedBundles > 0; i++) {
                int bundleUnit = priceList.get(i).getBundles();
                int numberOfBundles = unassignedBundles / bundleUnit;
                int remainder = unassignedBundles % bundleUnit;
                double subCost = numberOfBundles * priceList.get(i).getPrice();
                unassignedBundles = remainder;
                breakdowns.add(new Breakdown(numberOfBundles,bundleUnit,subCost));
            }
        }
        printCosts(orderBundles, priceList, breakdowns);
    }

    private void printCosts(int orderBundles, ArrayList<PriceList> priceList, ArrayList<Breakdown> breakdowns) {
        double totalCost = 0;
        for (Breakdown e: breakdowns) {
            totalCost = totalCost + e.getSubCost();
        }
        System.out.println(orderBundles + " " + priceList.get(0).getFormat() + " $" + totalCost);
        for (Breakdown e: breakdowns) {
            System.out.println(" " + e.getNumberOfBundles() + " x " + e.getBundleUnit() + " $" + e.getSubCost());
        }
    }

    private void importPriceList(String address) throws IOException {
        BufferedReader inputPriceList = null;
        try {
            inputPriceList = new BufferedReader(new FileReader(address));
            inputPriceList.readLine();
            inputPriceList.readLine();
            String line;
            while ((line = inputPriceList.readLine()) != null) {
                String tempStringArr[] = line.split("\\|");
                String format = tempStringArr[1].trim().toUpperCase();
                String tempPriceArr[] = tempStringArr[2].trim().split("[^0-9.']+");
                for (int i = 0; i < tempPriceArr.length - 1; i = i + 2) {
                    int bundles = Integer.parseInt(tempPriceArr[i]);
                    double price = Double.parseDouble(tempPriceArr[i + 1]);
                    switch (format) {
                        case "IMG":
                            this.imgPriceList.add(new PriceList(bundles, price, format));
                            break;
                        case "FLAC":
                            this.flacPriceList.add(new PriceList(bundles, price, format));
                            break;
                        case "VID":
                            this.vidPriceList.add(new PriceList(bundles, price, format));
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            inputPriceList.close();
        }
        this.imgPriceList.sort(Comparator.comparing(PriceList::getBundles).reversed());
        this.flacPriceList.sort(Comparator.comparing(PriceList::getBundles).reversed());
        this.vidPriceList.sort(Comparator.comparing(PriceList::getBundles).reversed());
    }

    private void importOrderInput(String address) throws IOException {
        BufferedReader input = null;
        try {
            String line;
            input = new BufferedReader(new FileReader(address));
            while ((line = input.readLine()) != null) {
                String tempStringArr[] = line.split(" ");
                this.order.put(tempStringArr[1], Integer.parseInt(tempStringArr[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            input.close();
        }
    }
}
