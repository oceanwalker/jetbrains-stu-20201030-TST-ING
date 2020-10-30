package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions.PatternsAndMatcher;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TestFindAWordWithAGivenLength {
    @Test
    public void test() {
        int[] numbers = new int[]{3, 11, 4};
        String[] texts = new String[]{"Java is the most popular programming language", "Regular expression is hard to read, isnt it?", "Wow! How awesome is that!"};
        String[] answers = new String[]{"YES", "NO", "YES"};
        for (int i = 0; i < numbers.length; i++) {
            assertEquals(answers[i], find(numbers[i], texts[i]));
        }
    }

    /**
     * 遍历一个单词的长度刚好等于
     *
     * @param number
     * @param text
     * @return
     */
    private String find(int number, String text) {
        Pattern pattern = Pattern.compile("\\b[a-zA-Z]{" + number + "}\\b");
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? "YES" : "NO";
    }
}
