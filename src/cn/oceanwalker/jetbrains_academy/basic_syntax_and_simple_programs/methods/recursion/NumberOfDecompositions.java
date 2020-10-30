package cn.oceanwalker.jetbrains_academy.basic_syntax_and_simple_programs.methods.recursion;

import java.util.ArrayList;
import java.util.List;

public class NumberOfDecompositions {

    public static String parse(int num) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(1);
        }
        print(list);
        mergeList(list, list.size());
        return null;
    }

    private static void print(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
            if (i != list.size() - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static void mergeList(List<Integer> list, int limit) {
        final int n = list.size();
        for (int i = 2; i <= limit; i++) {
            List<Integer> copyList = new ArrayList<>(list);
            for (int j = 0; j < n; j++) {
                if (copyList.get(j) == 1 && j <= n - i) {
                    for (int k = 0; k < i; k++) {
                        copyList.remove(j);
                    }
                    copyList.add(j, i);
                    print(copyList);
                    mergeList(copyList, i);
                    break;
                }
            }
        }
    }

    public static void partition(int n, int max, String prefix) {
        if (n == 0) {
            System.out.println(prefix);
        } else if (n > 0) {
            for (int i = 1; i <= max; i++) {
                partition(n - i, i, prefix + i + " ");
            }
        }
    }
}
