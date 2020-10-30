package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSequenceNjumberRepete {
    @Test
    public void test() {
        assertEquals("1 2 2 3 3 3 4 ", print(7));
    }

    private String print(int i) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        int number = 1;
        while (count < i) {
            for (int j = 0; j < number; j++) {
                result.append(number + " ");
                count++;
                if (count == i) {
                    break;
                }
            }
            number++;
        }
        return result.toString();
    }
}
