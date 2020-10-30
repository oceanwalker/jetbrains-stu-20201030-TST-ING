package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions.PatternsAndMatcher;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TestStartOrEndOfAWord {
    @Test
    public void test() {
        assertEquals("YES", match("ing", "Java is the most popular programming language"));
        assertEquals("NO", match("press", "Regular expressions is hard to read, isnt it?"));
        assertEquals("YES", match("ho", "Wow! How awesome is that!"));
        assertEquals("YES", match("ONE", "ponep,onep!"));
    }

    private String match(String part, String line) {
        Pattern pattern = Pattern.compile("(?i)\\b" + part + "|" + part + "\\b");
        Matcher matcher = pattern.matcher(line);
        return matcher.find() ? "YES" : "NO";
    }
}
