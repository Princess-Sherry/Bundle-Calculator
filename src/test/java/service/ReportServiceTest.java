package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void calculateCost(){
//        int targetAmount = 15;
//        List<Integer> amounts = Arrays.asList(new Integer[]{3, 6, 9});
//        List<Integer> res = rs.calculateCost(targetAmount, amounts);
//        System.out.println(res);

        int targetAmount2 = 13;
        List<Integer> amounts2 = Arrays.asList(new Integer[]{3, 5, 9});
        HashMap<Integer,Integer> res2 = rs.getBundleCombination(targetAmount2, amounts2);
        System.out.println(res2);
    }


}
