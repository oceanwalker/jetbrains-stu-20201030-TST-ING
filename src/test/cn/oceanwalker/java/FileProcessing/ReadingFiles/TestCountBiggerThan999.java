package test.cn.oceanwalker.java.FileProcessing.ReadingFiles;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestCountBiggerThan999 {
    @Test
    public void test() throws FileNotFoundException {
        Scanner scanner=new Scanner(new File("dataset_91022.txt"));
        int result=0;
        while (scanner.hasNextInt()){
            if(scanner.nextInt()>9999){
                result++;
            }
        }
        System.out.println(result);
    }
}
