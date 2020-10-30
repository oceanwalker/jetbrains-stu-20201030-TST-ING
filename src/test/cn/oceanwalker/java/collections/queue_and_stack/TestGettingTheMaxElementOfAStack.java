package test.cn.oceanwalker.java.collections.queue_and_stack;

import java.util.*;

//Sample Input:
//5
//push 2
//push 1
//max
//pop
//max
//
//Sample Output:
//2
//2
public class TestGettingTheMaxElementOfAStack {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        Deque<Integer> queue = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            String command = scanner.nextLine();
            if (command.startsWith("push")) {
                int value = Integer.parseInt(command.split(" ")[1]);
                max = Math.max(value, max);
                queue.offer(max);
            } else if (command.startsWith("pop")) {
                queue.pollLast();
                max = queue.getLast();
            } else {
                System.out.println(queue.getLast());
            }
        }
    }
}
