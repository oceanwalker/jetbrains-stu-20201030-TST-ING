package cn.oceanwalker.jetbrains_academy.java.Collections.queue_and_stack;

import cn.oceanwalker.utils.Utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser3 {

    public static String parse(String html) {
        html = Utils.removeSpaceBetweenAngleBracketsAndLine(html);
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("<.+?>");
        Matcher matcher = pattern.matcher(html);
        Deque<Integer> deque = new ArrayDeque<>();
        while (matcher.find()) {
            if (matcher.group().contains("/")) {
                result.append(html, deque.pollLast(), matcher.start()).append("\n");
            } else {
                deque.offer(matcher.end());
            }
        }
        return result.toString().substring(0, result.length() - 1);
    }
}
