package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestFindFactorial {
    @Test
    public void test() {
        assertEquals(13, findFactorial(6188989133L));
        assertEquals(4, findFactorial(6L));
    }

    private int findFactorial(long l) {
        int facIndex = 1;
        long fac = 1;
        while (fac <= l) {
            fac*=++facIndex;
        }
        return facIndex;
    }
}
