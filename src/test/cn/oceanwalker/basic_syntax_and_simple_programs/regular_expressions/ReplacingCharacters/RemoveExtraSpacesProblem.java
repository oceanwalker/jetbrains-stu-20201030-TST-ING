package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions.ReplacingCharacters;

import java.util.Scanner;

class RemoveExtraSpacesProblem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        // write your code here
        text = text.replaceAll("^\\s+|\\s+$", "");
        text = text.replaceAll("\\s+", " ");
        System.out.println(text);
    }
}
