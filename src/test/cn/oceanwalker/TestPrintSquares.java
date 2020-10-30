package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPrintSquares {
    @Test
    public void test() {
        String result = "1 " +
                "4 " +
                "9 " +
                "16 " +
                "25 " +
                "36 " +
                "49 ";
        assertEquals(result, print(50));
    }

    private String print(int i) {
        StringBuilder result = new StringBuilder();
        int n = 1;
        do {
            result.append(n * n + " ");
            n++;
        } while (n * n < i);
        return result.toString();
    }
}
