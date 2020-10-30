package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions.Regexes_in_programs;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class TestCheckIPAddress {
    @Test
    public void test() {
        String[] array = new String[]{"127.0.0.1", "256.0.0.1", "127.0.1", "192.168.123.231", "-1.0.0.1", "127.0.0.1."};
        String[] result = new String[]{"YES", "NO", "NO", "YES", "NO", "NO"};
        assertEquals(true, array.length == result.length);
        assertEquals(true, Arrays.equals(result, check(array)));
    }

    private String[] check(String[] array) {
        String[] result = new String[array.length];
//        final String digitReg = "(\\d|[1-9]\\d|1\\d\\d|2[0-4]\\d|25[0-5])";
        final String digitReg = "(1?\\d{1,2}|2[0-4]\\d|25[0-5])";
        String reg = String.format("(%s\\.){3}%s", digitReg, digitReg);
//        String reg = digitReg + dotReg + digitReg + dotReg + digitReg + dotReg + digitReg;
        System.out.println("reg=" + reg);
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i].matches(reg) ? "YES" : "NO";
        }
        System.out.println(Arrays.toString(result));
        return result;
    }
}
