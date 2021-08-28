package service;

import entity.Breakdown;
import entity.Report;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ReportService {
    private ArrayList<Report> reports = new ArrayList<Report>();
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    public void calculateAndPrintCost(OrderService os, PriceListService ps) {
        for (Map.Entry<String, Integer> order : os.getOrders().entrySet()) {
            LOGGER.info("Calculating costs for order " + order.toString());
            int targetAmount = order.getValue();
            String formatCode = order.getKey();
            HashMap<Integer, Double> bundles = ps.getFormatPriceListMapping().get(order.getKey()).getBundles();
            List<Integer> amounts = bundles.keySet().stream().collect(Collectors.toList());
            calculateCost(targetAmount, amounts, bundles, formatCode);
        }
        LOGGER.info("Print report to show costs for the orders");
        reports.forEach((report -> System.out.print(report.toString())));
    }

    private void calculateCost(int targetAmount, List<Integer> amounts, HashMap<Integer, Double> bundles, String formatCode) {
        double totalCost = 0;
        ArrayList<Breakdown> breakdowns = new ArrayList<Breakdown>();
        HashMap<Integer, Integer> bundleCombination = getBundleCombination(targetAmount, amounts);
        for(Map.Entry<Integer,Integer> bundleCombo: bundleCombination.entrySet()) {
            double subTotal = bundleCombo.getValue() * bundles.get(bundleCombo.getKey());
            breakdowns.add(new Breakdown(bundleCombo.getValue(),bundleCombo.getKey(),subTotal));
            totalCost = totalCost + subTotal;
        }
        breakdowns.sort(Comparator.comparing(Breakdown::getBundleUnit).reversed());
        reports.add(new Report(targetAmount,formatCode,totalCost,breakdowns));
    }

    public HashMap<Integer, Integer> getBundleCombination(int targetAmount, List<Integer> amounts){
        int[] minStorage = new int[targetAmount + 1];
        int[] minPath = new int[targetAmount + 1];

        for (int i = 1; i < minStorage.length; i++){
            minStorage[i] = 9999999;
        }
        minStorage[0] = 0;

        for(Integer amount: amounts){
            for (int j = amount; j < minStorage.length; j++){
                if(minStorage[j] > minStorage[j - amount] + 1){
                    minStorage[j] = minStorage[j - amount] + 1;
                    minPath[j] = amount;
                }
            }
        }
        HashMap<Integer, Integer> combos = new HashMap<Integer, Integer>();
        int s = targetAmount;
        while(s != 0){
            if (!combos.containsKey(minPath[s])) {
                combos.put(minPath[s],1);
            } else {
                combos.put(minPath[s], combos.get(minPath[s]) + 1);
            }
            s -= minPath[s];
        }
        return combos;
    }
}
