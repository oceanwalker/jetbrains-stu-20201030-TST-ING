package cn.oceanwalker.jetbrains_academy.java.Collections.queue_and_stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser {
    public static String parse(String input) {
        input = input.replaceAll("\n", "");
        input = removeSpaceBetweenAngleBrackets(input);
        StringBuilder print = new StringBuilder();
        decode(input, print);
        if (print.lastIndexOf("\n") == print.length() - 1) {
            print.delete(print.length() - 1, print.length());
        }
        return print.toString();
    }

    public static String removeSpaceBetweenAngleBrackets(String str) {
        return str.replaceAll(">\\s+<", "><");
    }

    private static void decode(String html, StringBuilder print) {
        if (!hasChild(html)) {
            print.append(getContent(html) + "\n");
        } else {
            String content = getContent(html);
            List<String> list = getList(content);
            for (String s : list) {
                decode(s, print);
            }
            print.append(content + "\n");
        }
    }

    public static String getContent(String html) {
        return html.substring(html.indexOf(">") + 1, html.lastIndexOf("<"));
    }

    public static boolean hasChild(String html) {
        return html.split(">").length > 3;
    }

    public static List<String> getList(String content) {
        List<String> list = new ArrayList<>();
        int fromIndex = 0;
        Deque<String> deque = new ArrayDeque<>();
        while (fromIndex < content.length()) {
            final int beginIndex = content.indexOf("<", fromIndex) + 1;
            String key = content.substring(beginIndex, content.indexOf(">", fromIndex));
            deque.offer("<" + key + ">");
            Pattern pattern = Pattern.compile("</?" + key + ">");
            Matcher matcher = pattern.matcher(content);
            matcher = matcher.region(beginIndex, content.length());
            while (matcher.find()) {
                final String curTag = matcher.group();
                if (curTag.equals(deque.getLast())) {
                    deque.offer(curTag);
                } else {
                    deque.pollLast();
                }
                if (deque.isEmpty()) {
                    int endIndex = matcher.end();
                    String target = content.substring(fromIndex, endIndex);
                    list.add(target);
                    fromIndex = endIndex;
                }
            }
        }
        return list;
    }
}
