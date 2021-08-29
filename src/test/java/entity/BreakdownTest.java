package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreakdownTest {
    Breakdown breakdown;

    @BeforeEach
    void setUp(){
        breakdown = new Breakdown(0,0,0);
    }

    @Test
    @DisplayName("Test breakdown toString")
    void testToString(){
        breakdown.setNumberOfBundles(2);
        breakdown.setBundleUnit(3);
        breakdown.setSubTotal(500);
        assertEquals(" 2 x 3 $500.0", breakdown.toString());
    }
}
