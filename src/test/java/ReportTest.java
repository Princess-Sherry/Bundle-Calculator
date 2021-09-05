import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReportTest {
    private Report report;

    @BeforeEach
    public void setUp(){
        report = new Report(0,"",0,null);
    }

    @Test
    @DisplayName("Test report toString")
    public void testToString() {
        ArrayList<Breakdown> breakdowns = new ArrayList<>();
        breakdowns.add(new Breakdown(2,3,100));
        breakdowns.add(new Breakdown(3,6,400));
        report.setBreakdowns(breakdowns);
        report.setOrderAmount(10);
        report.setOrderFormatCode("IMG");
        report.setTotalCost(600);
        assertEquals("10 IMG $600.0\n" + " 2 x 3 $100.0\n" + " 3 x 6 $400.0\n", report.toString());
    }
}
