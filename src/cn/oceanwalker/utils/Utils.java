package cn.oceanwalker.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static String removeSpace(String value) {
        value = value.replaceAll("\\s", "");
        return value;
    }

    public static String removeSpaceAndLine(String value) {
        value = value.replaceAll("\\s", "").replaceAll("\r\n", "").replaceAll("\n", "");
        return value;
    }

    public static String removeSpaceBetweenAngleBrackets(String str) {
        return str.replaceAll(">\\s+<", "><");
    }

    public static String removeSpaceBetweenAngleBracketsAndLine(String html) {

        return html.replaceAll(">\\s+<", "><").replaceAll("\n", "");
    }

    public static String readFileAsStringByPath(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

    public static String getPathByRelative(Class aClass, String fileName) {
        String path = aClass.getResource(fileName).getPath();
        path = path.substring(1).replace("/", "//");
        return path;
    }

    public static String getFileContent(Class<?> aClass, String fileName) throws IOException {
        return readFileAsStringByPath(getPathByRelative(aClass, fileName));
    }

    public static String removeLine(String json) {
        return json.replaceAll("\r\n", "").replaceAll("\n", "");
    }

    public static String removeLastLine(String text) {
        return text.substring(0, text.length() - 4);
    }

    public static String removeSpaceAndLineInJSON(String input) {
        if (null == input) {
            return "";
        }
        return input
                .replaceAll("\n", "")
                .replaceAll("\\{\\s+\"", "{\"")
                .replaceAll("\"\\s+}", "\"}")
                .replaceAll("(\\w)\\s+}", "$1}")
                .replaceAll(",\\s+\"", ",\"")
                .replaceAll("}\\s+", "}")
                ;
    }

}