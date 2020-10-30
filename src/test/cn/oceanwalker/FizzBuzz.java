package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FizzBuzz {
    @Test
    public void test() {
        assertEquals("Fizz4Buzz", getFizzBuzz(3, 5));
        assertEquals("FizzBuzz16", getFizzBuzz(15, 16));
    }

    private String getFizzBuzz(int i, int j) {
        StringBuilder result = new StringBuilder();
        for (int k = i; k <= j; k++) {
            if (k % 3 == 0) {
                result.append("Fizz");
            }
            if (k % 5 == 0) {
                result.append("Buzz");
            }
            if (k % 3 != 0 && k % 5 != 0) {
                result.append(k);
            }
        }
        return result.toString();
    }
}
