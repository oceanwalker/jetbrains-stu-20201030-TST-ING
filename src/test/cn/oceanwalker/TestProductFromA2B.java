package test.cn.oceanwalker;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class TestProductFromA2B {
    @Test
    public void test() {
        assertEquals("1", product(1, 2));
        assertEquals("11035502400", product(100, 105));
    }

    private String product(int i, int j) {
        BigInteger b = new BigInteger(String.valueOf(i));
        for (int k = i + 1; k < j; k++) {
            b = b.multiply(new BigInteger(String.valueOf(k)));
        }
        return b.toString();
    }
}
