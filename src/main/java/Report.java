import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

/**
 * A class for each report of total cost and cost breakdown for a certain type of order
 */
@Getter @Setter
@AllArgsConstructor
public class Report {
    private int orderAmount;
    private String orderFormatCode;
    private double totalCost;
    ArrayList<Breakdown> breakdowns;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(orderAmount).append(" ").append(orderFormatCode).append(" $").append(totalCost).append("\n");
        breakdowns.forEach(breakdown -> sb.append(breakdown).append("\n"));
        return sb.toString();
    }
}
