package service;

import entity.input.base.BasePriceList;
import entity.input.base.Bundle;
import entity.output.Breakdown;
import entity.output.TotalCostLine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class CostService {
    private LinkedHashMap<TotalCostLine, ArrayList<Breakdown>> costOutput;

    public void calculateAndPrintCost(OrderService os, PriceListService ps) {
        for (Map.Entry<String, Integer> order : os.getOrders().entrySet()) {
            for (Map.Entry<String, BasePriceList> priceListMap : ps.getFormatPriceListMapping().entrySet()) {
                if (order.getKey().equals(priceListMap.getKey())) {
                    calculateCost(order, priceListMap.getValue().getBundles());
                }
            }
        }
    }

    private void calculateCost(Map.Entry order, ArrayList<Bundle> bundles) {
        int orderBundles = (int) order.getValue();
        String orderFormatCode = (String) order.getKey();
        bundles.sort(Comparator.comparing(Bundle::getAmount).reversed());

        int unassignedBundles = orderBundles;
        ArrayList<Breakdown> breakdowns = new ArrayList<Breakdown>();
        for (int k = 0; k < bundles.size() && unassignedBundles != 0; k++) {
            breakdowns.clear();
            unassignedBundles = orderBundles;
            for (int i = k; i < bundles.size() && unassignedBundles > 0; i++) {
                int bundleUnit = bundles.get(i).getAmount();
                int numberOfBundles = unassignedBundles / bundleUnit;
                int remainder = unassignedBundles % bundleUnit;
                double subCost = numberOfBundles * bundles.get(i).getPrice();
                unassignedBundles = remainder;
                breakdowns.add(new Breakdown(numberOfBundles,bundleUnit,subCost));
            }
        }
        printCosts(orderBundles, orderFormatCode, breakdowns);
    }


    private void printCosts(int orderBundles, String orderFormatCode, ArrayList<Breakdown> breakdowns) {
        double totalCost = 0;
        for (Breakdown e : breakdowns) {
            totalCost = totalCost + e.getSubCost();
        }
        System.out.println(orderBundles + " " + orderFormatCode + " $" + totalCost);
        for (Breakdown e : breakdowns) {
            System.out.println(" " + e.getNumberOfBundles() + " x " + e.getBundleUnit() + " $" + e.getSubCost());
        }
    }
}
