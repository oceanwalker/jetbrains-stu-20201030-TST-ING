package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestBusTour {
    @Test
    public void test() {
        assertEquals(-1, heightTry(234, 8, new int[]{465, 453, 981, 463, 1235, 871, 475, 981}));
        assertEquals(2, heightTry(211, 5, new int[]{871, 205, 123, 871, 1681}));
    }

    private int heightTry(int busHeight, int brigeNumber, int[] brigeHeights) {
        int result = -1;
        for (int i = 0; i < brigeHeights.length; i++) {
            if (busHeight >= brigeHeights[i]) {
                result = i + 1;
                break;
            }
        }
        return result;
    }
}
