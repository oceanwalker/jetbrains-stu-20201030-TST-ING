package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions.Regexes_in_programs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSafePassword {
    @Test
    public void test() {
        assertEquals(true, isSafe("Password1234"));
        assertEquals(false, isSafe("SuperSecretPass"));
    }

    private boolean isSafe(String pass) {
        String upperReg = ".*[A-Z].*";
        String lowerReg = ".*[a-z].*";
        String digitReg = ".*\\d.*";
        return pass.matches(upperReg) &&
                pass.matches(lowerReg) &&
                pass.matches(digitReg) &&
                pass.length() >= 12;
    }
}
