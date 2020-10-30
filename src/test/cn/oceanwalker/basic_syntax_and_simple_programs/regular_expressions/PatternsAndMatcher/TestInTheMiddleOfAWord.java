package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions.PatternsAndMatcher;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TestInTheMiddleOfAWord {
    @Test
    public void test() {
        assertEquals("YES", match("Gramm", "Java is the most popular programming language"));
        assertEquals("YES", match("Press", "Regular expressions is hard to read, isnt it?"));
        assertEquals("NO", match("some", "Wow! How awesome is that!"));
    }

    private String match(String part, String line) {
        Pattern pattern = Pattern.compile("(?i)\\b\\w+" + part + "\\w+\\b");
        Matcher matcher = pattern.matcher(line);
        return matcher.find() ? "YES" : "NO";
    }

}
