package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPercentageCharacters {
    @Test
    public void test() {
        assertEquals("40.0", count("acggtgttat"));
    }

    private String count(String target) {
        double result = 0;
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == 'g'||target.charAt(i) == 'c') {
                count++;
            }
        }
        return (double)count / target.length()*100 + "";
    }
}
