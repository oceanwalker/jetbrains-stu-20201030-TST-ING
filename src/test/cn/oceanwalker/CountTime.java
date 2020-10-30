package test.cn.oceanwalker;

import org.junit.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class CountTime {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入需要统计分钟的文本：");
        int minutes = 0;
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();
            minutes += countDigit(text);
            if(text.contains("@")){
                break;
            }
        }
        System.out.println("学完这些至少需要：" + minutes + " 分钟！，也就是 " + minutes / 60 + " 个小时 " + minutes % 60 + " 分钟 加油吧少年！");
    }

    @Test
    public void test() {
        assertEquals(20, countDigit("asdf(20asdfsadf30asdfasdf50"));
    }

    private static int countDigit(String text) {
        Pattern pattern = Pattern.compile("\\(\\d+");
        Matcher matcher = pattern.matcher(text);
        int count = 0;
        while (matcher.find()) {
            count += Integer.parseInt(matcher.group().substring(1));
        }
        return count;
    }
}
