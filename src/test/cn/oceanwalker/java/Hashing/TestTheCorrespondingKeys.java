package test.cn.oceanwalker.java.Hashing;

import org.junit.Test;

public class TestTheCorrespondingKeys {
    @Test
    public void test() {
        int[] a = {78, 41, 33};
        for (int i : a) {
            System.out.println(convert(i));
        }
    }

    private int convert(int k) {
        return ((k * 7) + 42) % 71;
    }
}
