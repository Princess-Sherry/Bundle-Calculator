import lombok.Getter;

import java.util.*;
import java.util.logging.Logger;

/**
 * This ReportService Class is to print out the cost reports
 */
@Getter
public class ReportService {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class.getName());
    private final ArrayList<Report> reports = new ArrayList<>();

    public void printReports() {
        LOGGER.info("Print out cost reports for the orders.");
        this.reports.forEach((report -> System.out.print(report.toString())));
    }
}
