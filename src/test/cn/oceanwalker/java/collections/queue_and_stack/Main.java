package test.cn.oceanwalker.java.collections.queue_and_stack;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final var brackets = new Scanner(System.in).nextLine();
        var stack = 0L;
        var isBalanced = true;

        for (int i = 0; isBalanced && i < brackets.length(); i++) {
            final var index = "([{ }])".indexOf(brackets.charAt(i)) - 3;
            if (index < 0) {
                stack <<= 2;
                stack |= -index;
            } else {
                isBalanced = stack % 4 == index;
                stack >>= 2;
            }

        }
        System.out.println(isBalanced && stack == 0L);
    }
}
