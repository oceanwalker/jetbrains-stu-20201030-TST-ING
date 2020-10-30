package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestArithMeticAverage {
    @Test
    public void test() {
        assertEquals(true, Double.compare(4.5, average(-5, 12)) == 0);
    }

    private double average(int start, int end) {

        double result = 0;
        int number = 0;
        for (int i = start; i <= end; i++) {
            if (i % 3 != 0) {
                continue;
            }
            result += i;
            number++;
        }
        result = result / number;
        return result;
    }
}
