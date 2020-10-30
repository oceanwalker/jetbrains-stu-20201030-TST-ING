package test.cn.oceanwalker.basic_syntax_and_simple_programs.regular_expressions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOTest {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d{3,5}");
        String charSequence = "123-34345-234-00";
        Matcher matcher = pattern.matcher(charSequence);

        //虽然匹配失败，但由于charSequence里面的"123"和pattern是匹配的,所以下次的匹配从位置4开始
        print(matcher.matches());  //虽然整个匹配失败了,但是有部分匹配(123,所以于是重置了下次匹配的位置从3开始),
        // 所以后面调用matcher.find()的时候,调用matcher.start()匹配成功是从4开始
        //测试匹配位置
        matcher.find();
        print(matcher.start());   //如果注释掉前面的print(matcher.matches());,这里会打印出0

        //使用reset方法重置匹配位置
        matcher.reset();

        //第一次find匹配以及匹配的目标和匹配的起始位置
        print(matcher.find());
        print(matcher.group() + " - " + matcher.start());
        //第二次find匹配以及匹配的目标和匹配的起始位置
        print(matcher.find());
        print(matcher.group() + " - " + matcher.start());

        //第一次lookingAt匹配以及匹配的目标和匹配的起始位置
        print(matcher.lookingAt());
        print(matcher.group() + " - " + matcher.start());

        //第二次lookingAt匹配以及匹配的目标和匹配的起始位置
        print(matcher.lookingAt());
        print(matcher.group() + " - " + matcher.start());
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
