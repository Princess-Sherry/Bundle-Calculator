import lombok.Getter;

import java.util.*;

/**
 * This ReportService Class is to run the calculation and then print out the cost reports
 */
@Getter
public class ReportService {
    private final ArrayList<Report> reports = new ArrayList<>();

    public void printReports() {
        this.reports.forEach((report -> System.out.print(report.toString())));
    }
}
