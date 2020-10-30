package test.cn.oceanwalker.java.generics.wildcards;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class to modify
 */
public class ListMultiplicator {

    /**
     * Repeats original list content provided number of times
     *
     * @param list list to repeat
     * @param n    times to repeat, should be zero or greater
     */
    public static void multiply(List<?> list, int n) {
        // Add implementation here
        fixed(list, n);
    }

    static <T> void fixed(List<T> list, int n) {
        if (n == 0) {
            list.clear();
        } else if (n > 1) {
            List<T> copy = new ArrayList<>(list);
            for (int i = 1; i < n; i++) {
                list.addAll(copy);
            }
        }
    }

    @Test
    public void test() {
        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(1, 2, 3));
        multiply(list, 2);
        assertEquals(Arrays.asList(1, 2, 3, 1, 2, 3), list);
    }
}