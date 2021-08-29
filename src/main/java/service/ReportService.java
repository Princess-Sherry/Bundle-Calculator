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
        BundleComboCalculator bundleComboCalculator = new BundleComboCalculator(targetAmount,amounts);
        HashMap<Integer, Integer> bundleCombination = bundleComboCalculator.getBundleCombination();
        for(Map.Entry<Integer,Integer> bundleCombo: bundleCombination.entrySet()) {
            double subTotal = bundleCombo.getValue() * bundles.get(bundleCombo.getKey());
            breakdowns.add(new Breakdown(bundleCombo.getValue(),bundleCombo.getKey(),subTotal));
            totalCost = totalCost + subTotal;
        }
        breakdowns.sort(Comparator.comparing(Breakdown::getBundleUnit).reversed());
        reports.add(new Report(targetAmount,formatCode,totalCost,breakdowns));
    }
}
