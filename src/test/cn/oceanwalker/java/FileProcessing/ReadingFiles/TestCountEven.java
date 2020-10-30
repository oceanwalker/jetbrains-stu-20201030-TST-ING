package test.cn.oceanwalker.java.FileProcessing.ReadingFiles;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestCountEven {
    @Test
    public void test() throws FileNotFoundException {
        Scanner scanner=new Scanner(new File("dataset_91065.txt"));
        int result=0;
        while (scanner.hasNextInt()){
            final int n = scanner.nextInt();
            if(n==0){
                break;
            }
            if(n %2==0){
                result++;
            }
        }
        System.out.println(result);
    }
}
