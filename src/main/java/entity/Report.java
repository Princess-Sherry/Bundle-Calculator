package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;

@Getter
@AllArgsConstructor
public class Report {
    private int orderAmount;
    private String orderFormatCode;
    private double totalCost;
    ArrayList<Breakdown> breakdowns;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(orderAmount + " " + orderFormatCode + " $" + totalCost + "\n");
        breakdowns.forEach(breakdown -> sb.append(breakdown));
        return sb.toString();
    }
}
