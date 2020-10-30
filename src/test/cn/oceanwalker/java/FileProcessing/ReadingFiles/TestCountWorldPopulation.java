package test.cn.oceanwalker.java.FileProcessing.ReadingFiles;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestCountWorldPopulation {
    @Test
    public void test() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("dataset_91069.txt"));
        int result = 0;
        Long max = Long.MIN_VALUE;
        Long lastPopulation = 0L;
        scanner.nextLine();
        while (scanner.hasNext()) {
            int year = Integer.parseInt(scanner.next());
            Long population = Long.parseLong(scanner.next().replace(",", ""));
            final long increase = population - lastPopulation;
            System.out.println(increase);
            if (lastPopulation != 0 && increase > max) {
                result = year;
                max=increase;
            }
            lastPopulation = population;
        }
        System.out.println(result);
    }
}
