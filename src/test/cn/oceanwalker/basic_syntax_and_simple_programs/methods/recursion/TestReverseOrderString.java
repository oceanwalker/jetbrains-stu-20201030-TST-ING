package test.cn.oceanwalker.basic_syntax_and_simple_programs.methods.recursion;

import org.junit.Test;

import java.util.Scanner;

public class TestReverseOrderString {
    @Test
    public void test() {
        String input="bac";
        Main.printReverseCharByChar(input);
    }
}

class Main {
    /* Fix this method */
    public static void printReverseCharByChar(String s) {
        if (s.length() > 0) {
            printReverseCharByChar(s.substring(1));
            System.out.print(s.charAt(0));
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        printReverseCharByChar(scanner.nextLine());
    }
}