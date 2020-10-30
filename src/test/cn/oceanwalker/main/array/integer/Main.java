package test.cn.oceanwalker.main.array.integer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println(count(array));
    }


    private static int count(int[] array) {
        int longest = 1;
        int curLongest = 1;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] < array[i + 1]) {
                curLongest++;
                if (curLongest > longest) {
                    longest = curLongest;
                }
            } else {
                curLongest = 1;
            }
        }
        return longest;
    }
}

