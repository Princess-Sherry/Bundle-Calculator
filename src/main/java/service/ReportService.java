package service;

import entity.Breakdown;
import entity.Report;
import lombok.Getter;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Getter
public class ReportService {
    private ArrayList<Report> reports = new ArrayList<Report>();
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());

    public void calculateAndPrintCost(OrderService os, PriceListService ps) {
        os.getOrders().forEach((orderFormatKey,targetAmountValue) -> {
            LOGGER.info("Calculating costs for order " + targetAmountValue + " " + orderFormatKey);
            ps.getFormatPriceListMapping().forEach((formatCodeKey,priceList) -> {
                if (orderFormatKey.equals(formatCodeKey)) {
                    calculateCost(orderFormatKey,targetAmountValue,priceList.getBundles());
                }
            });
        });
        LOGGER.info("Print report to show costs for the orders");
        reports.forEach((report -> System.out.print(report.toString())));
    }

    private void calculateCost(String formatCode, int targetAmount, HashMap<Integer,Double> bundles) {
        List<Integer> amounts = bundles.keySet().stream().collect(Collectors.toList());
        HashMap<Integer, Integer> bundleCombination = new BundleComboCalculator(targetAmount,amounts).getBundleCombination();

        ArrayList<Breakdown> breakdowns = new ArrayList<Breakdown>();
        bundleCombination.forEach((bundleUnit,amount) -> {
            double subTotal = amount * bundles.get(bundleUnit);
            breakdowns.add(new Breakdown(amount,bundleUnit,subTotal));
        });
        breakdowns.sort(Comparator.comparing(Breakdown::getBundleUnit).reversed());

        double totalCost = breakdowns.stream().mapToDouble(Breakdown::getSubTotal).sum();

        reports.add(new Report(targetAmount,formatCode,totalCost,breakdowns));
    }
}
