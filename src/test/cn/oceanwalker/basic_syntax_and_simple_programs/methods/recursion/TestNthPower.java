package test.cn.oceanwalker.basic_syntax_and_simple_programs.methods.recursion;

import cn.oceanwalker.jetbrains_academy.basic_syntax_and_simple_programs.methods.recursion.NthPower;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNthPower {
    @Test
    public void test() {
        assertEquals(2.0, NthPower.pow(2.0, 1), 0);
        assertEquals(57.665, NthPower.pow(1.5, 10), 0.001);
    }
}
