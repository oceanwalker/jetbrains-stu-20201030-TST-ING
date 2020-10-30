package test.cn.oceanwalker.java.collections.queue_and_stack;

import java.util.*;

public class TestWhichBracketsAreBalanced {
    public static void main(String[] args) {
        // put your code here
        Map<Character, Character> map = new HashMap<>(100);
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        Scanner scanner = new Scanner(System.in);
        String uncheck = scanner.next();
        boolean result = true;
        Deque<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < uncheck.length(); i++) {
            final char c = uncheck.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                queue.offer(c);
            } else {
                final Character poll = queue.pollLast();
                if (null == poll || c != map.get(poll)) {
                    result = false;
                    break;
                }
            }
        }
        if (!queue.isEmpty()) {
            result = false;
        }
        System.out.println(result);
    }
}