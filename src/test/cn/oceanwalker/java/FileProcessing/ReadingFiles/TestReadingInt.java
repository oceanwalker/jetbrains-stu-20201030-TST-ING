package test.cn.oceanwalker.java.FileProcessing.ReadingFiles;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestReadingInt {
    @Test
    public void test(){
        String pathToFile = "str.txt";
        try (Scanner scanner = new Scanner(new File(pathToFile))) {
            System.out.println(scanner.nextInt());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
