package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions.PatternsAndMatcher;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TestNumberParsing {
    @Test
    public void test() {
        String[] lines = new String[]{"123"
                , "0123"
                , "-56.0"
                , "-0,05"
                , "-23.230"
                , "+12376352189432535347623423443417,36817231873134176345178234234234234"
                , "adsf"
                , "0"
                , "1."
                , "1.00"
                , " "
                , ""
                , "-012313573"
                /**
                 * 正或负：[-+]?
                 * 整数一位\\d
                 * 整数 n 位[1-9]\\d*
                 * 零：0
                 * 小数一位：整数后面添加[\\.,]？\\d
                 * 小数n位以零结尾：整数后面添加 n 位[\\.,]？\\d*[1-9]0
                 * 小数n位不以零结尾：整数后面添加 n 位[\\.,]？\\d*[1-9]
                 * 不含小数部分
                 * [-+]?(\\d|[1-9]\\d*|0)([\\.,]?\\d|[\\.,]?\\d*[1-9]0|[\\.,]?\\d*[1-9]|)
                 *
                 *
                Pattern pattern = Pattern.compile("[-+]?(\\d|[1-9]\\d*|0)([\\.,]?\\d|[\\.,]?\\d*[1-9]0|[\\.,]?\\d*[1-9])")
                */
        };
        String[] answers = new String[]{
                "YES"
                , "NO"
                , "YES"
                , "YES"
                , "NO"
                , "YES"
                , "NO"
                , "YES"
                , "NO"
                , "NO"
                , "NO"
                , "NO"
                , "NO"
        };
        assertEquals(true, lines.length == answers.length);
        for (int i = 0; i < lines.length; i++) {
            assertEquals(answers[i], checkRightAndWrong(lines[i]));
            assertEquals(answers[i], checkRight(lines[i]));
            System.out.println("success " + (i + 1));
        }

    }

    private String checkRight(String line) {
        Pattern pattern = Pattern.compile("[-+]?(0|\\d|[1-9]\\d*)([\\.,](\\d|\\d*[1-9]))?");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches() ? "YES" : "NO";
    }

    private static String checkRightAndWrong(String line) {
        Pattern rightPattern = Pattern.compile("[-+]?\\d+[\\.,]?\\d*");
        Matcher rightMatcher = rightPattern.matcher(line);
        Pattern wrongPattern = Pattern.compile("^[-+]?0\\d|\\d0$|\\.$");
        Matcher wrongMatcher = wrongPattern.matcher(line);
        boolean result;
        final boolean isRight = rightMatcher.matches();
        final boolean isFalse = wrongMatcher.find();
//        System.out.println("line = " + line + " isRight = " + isRight + " isFalse = " + isFalse);
        result = isRight && !isFalse;
        return result ? "YES" : "NO";
    }
}
