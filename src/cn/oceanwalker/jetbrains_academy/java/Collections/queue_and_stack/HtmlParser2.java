package cn.oceanwalker.jetbrains_academy.java.Collections.queue_and_stack;

import cn.oceanwalker.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParser2 {
    public static String parse(String html) {
        html = Utils.removeSpaceBetweenAngleBrackets(html);
        html = html.replaceAll("\n", "");
        StringBuilder result = new StringBuilder();
        Pattern pattern = Pattern.compile("<(\\w+)>(.+?)</\\1>", Pattern.MULTILINE | Pattern.DOTALL);
        decode(html, pattern, result);
        return result.toString().substring(0, result.length() - 1);
    }

    private static void decode(String html, Pattern pattern, StringBuilder result) {
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String content = matcher.group(2);
            if (content.contains("<")) {
                decode(content, pattern, result);
            }
            result.append(content + "\n");
        }
    }
}
