package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions.MatchResults;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestFindAllPasswords {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        // write your code here
        match(text);
    }

    private static void match(String text) {
        Pattern pattern = Pattern.compile("(password[:\\s]*)(\\w+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            do {
                System.out.println(matcher.group(2));
            } while (matcher.find());
        } else {
            System.out.println("No passwords found.");
        }
    }
}
