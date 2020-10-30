package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCargoCount {
    @Test
    public void test() {
        int[] sizes = new int[]{0, -1, -1, 1, 0, 0, 0, 0, 0, 1, 0, -1, 1, -1};
        assertEquals("7 3 4", count(sizes));
    }

    private String count(int[] sizes) {
        int small = 0;
        int perfect = 0;
        int large = 0;
        for (int size : sizes) {
            if (size > 0) {
                large++;
            } else if (size == 0) {
                perfect++;
            } else {
                small++;
            }
        }
        return perfect + " " + large + " " + small;
    }
}

//import java.util.LinkedHashMap;
//        import java.util.Map;
//        import java.util.Scanner;
//        import java.util.stream.IntStream;
//
//class Main {
//    public static void main(String[] args) {
//        inputAndSolve().values().forEach(i -> System.out.printf("%d ", i));
//    }
//
//    static Map<Integer, Integer> inputAndSolve() {
//        Scanner scanner = new Scanner(System.in);
//        final Map<Integer, Integer> totals = generateTotals();
//        IntStream.generate(scanner::nextInt)
//                .limit(scanner.nextInt())
//                .forEach(i -> totals.computeIfPresent(i, (key, old) -> old + 1));
//        return totals;
//    }
//
//    private static Map<Integer, Integer> generateTotals() {
//        final Map<Integer, Integer> totals = new LinkedHashMap<>(3);
//        totals.put(0, 0);
//        totals.put(1, 0);
//        totals.put(-1, 0);
//        return totals;
//    }
//}