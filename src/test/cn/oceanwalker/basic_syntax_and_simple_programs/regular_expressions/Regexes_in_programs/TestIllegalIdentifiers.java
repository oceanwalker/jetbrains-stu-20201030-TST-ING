package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions.Regexes_in_programs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestIllegalIdentifiers {
    @Test
    public void test() {
        String[] strings = new String[]{"ident", "i", "__", "1a", "b33", "_n1", "_", "_a_"};
        assertEquals("__" + "1a" + "_", identifierFail(strings));
        assertEquals("__" + "1a" + "_", identifierSuccess(strings));
    }

    private String identifierSuccess(String[] strings) {
        StringBuilder result = new StringBuilder();
//        String reg = "_[a-zA-Z\\d]\\w*|[a-zA-Z]\\w*";
        String reg = "([a-zA-Z][\\w]*|[_][a-zA-Z0-9]+)";

        for (String string : strings) {
            if (!string.matches(reg)) {
                result.append(string);
            }
        }
        return result.toString();
    }

    private String identifierFail(String[] strings) {
        StringBuilder result = new StringBuilder();
        String reg = "_|_[^a-zA-Z\\d].*|[^a-zA-Z_].*|.*[^\\w]+.*";
        for (String string : strings) {
            if (string.matches(reg)) {
                result.append(string);
            }
        }
        return result.toString();
    }
}
