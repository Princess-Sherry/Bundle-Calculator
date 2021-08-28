package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReportServiceTest {
    ReportService rs;

    @BeforeEach
    void setUp(){
        rs = new ReportService();
    }

    @Test
    void testGetBundleCombination(){
        int targetAmount = 13;
        List<Integer> amounts = Arrays.asList(new Integer[]{3, 5, 9});
        HashMap<Integer,Integer> result = rs.getBundleCombination(targetAmount, amounts);
        assertEquals(1,result.get(3));
        assertEquals(2,result.get(5));
    }
}
