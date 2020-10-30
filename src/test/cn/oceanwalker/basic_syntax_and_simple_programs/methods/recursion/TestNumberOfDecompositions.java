package test.cn.oceanwalker.basic_syntax_and_simple_programs.methods.recursion;

import cn.oceanwalker.jetbrains_academy.basic_syntax_and_simple_programs.methods.recursion.NumberOfDecompositions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestNumberOfDecompositions {
    @Test
    public void test() {
//        assertEquals("1 1 1 1 1\n" +
//                "2 1 1 1\n" +
//                "2 2 1\n" +
//                "3 1 1\n" +
//                "3 2\n" +
//                "4 1\n" +
//                "5", );
        int input = 5;
        NumberOfDecompositions.parse(input);
        NumberOfDecompositions.partition(input, input, "");
    }
}
