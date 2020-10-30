package test.cn.oceanwalker.json_xml_converter.stage2;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.ConvertUtils;
import cn.oceanwalker.utils.Utils;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class TestStage2ConvertingAttributes {
    @Test
    public void test() {
        var map = new LinkedHashMap<String, String>();
        map.put("{ \"employee\" : { \"@department\" : \"manager\", \"#employee\" : \"Garry Smith\" } }", "<employee department = \"manager\">Garry Smith</employee>");
        map.put("{\"host\":\"127.0.0.1\"}", "<host>127.0.0.1</host>");
        map.put("{ \"person\" : { \"@rate\" : \"1\", \"@name\" : \"Torvalds\", \"#person\" : null } }", "<person rate = \"1\" name = \"Torvalds\" />");
        map.put("{\"success\": null }", "<success/>");
        map.put("<jdk>1.8.9</jdk>", "{\"jdk\" : \"1.8.9\"}");
        map.put("<storage/>", "{ \"storage\" : null }");
        map.put("<employee department = \"manager\">Garry Smith</employee>", "{ \"employee\" : { \"@department\" : \"manager\", \"#employee\" : \"Garry Smith\" } }");
        map.put("<person rate = \"1\" name = \"Torvalds\" />", "{ \"person\" : { \"@rate\" : 1, \"@name\" : \"Torvalds\", \"#person\" : null } }");
        map.put("{\"pizza\":{\"@size\":\"2\",\"#pizza\":\"1\"}}", "<pizza size=\"2\">1</pizza>");
        map.put("{\"pizza\":{\"@size\":\"2\",\"#pizza\":\"12345\"}}", "<pizza size=\"2\">12345</pizza>");
        map.put("<pizza size=\"2\">12345</pizza>", "{\"pizza\":{\"@size\":\"2\",\"#pizza\":\"12345\"}}");
        map.put("<pizza size=\"2345\">12345</pizza>", "{\"pizza\":{\"@size\":\"2345\",\"#pizza\":\"12345\"}}");
        map.put("<numbers digits=\"4\">1234</numbers>", "{\"numbers\":{\"@digits\":4,\"#numbers\":1234}}");
        for (var keySet : map.entrySet()) {
            assertEquals("input= " + keySet.getValue(), Utils.removeSpace(keySet.getKey()), Utils.removeSpace(convert(keySet.getValue())));
        }
    }

    private static String convert(String input) {
        StringBuilder result = new StringBuilder();
        final boolean isXml = input.startsWith("<");
        if (isXml) {
            convertXML(input, result);
        } else {
            convertJSON(input, result);
        }
        return result.toString();
    }

    private static void convertJSON(String input, StringBuilder result) {
        String mainKey = input.split("\"")[1];
        Pattern pattern = Pattern.compile("(\")([@#]?\\w+)(\"\\s*:\\s*)(\"[\\w.\\s]+?\"|[\\d]+|null)");
        Matcher matcher = pattern.matcher(input);
        var map = new LinkedHashMap<String, String>();
        boolean isMainValueNull = false;
        while (matcher.find()) {
            String key = matcher.group(2);
            String value = matcher.group(4);
            if (key.startsWith("#") && value.equals("null")) {
                isMainValueNull = true;
                continue;
            } else {
                if (key.startsWith("#") || key.startsWith("@")) {
                    key = key.substring(1);
                }
                if (!value.startsWith("\"") && !"null".equals(value)) {
                    value = "\"" + value + "\"";
                }
                map.put(key, value);
            }
        }
        if (map.getOrDefault(mainKey, "null").equals("null")) {
            isMainValueNull = true;
        }

        result.append(mainKey);
        for (var pair : map.entrySet()) {
            final String key = pair.getKey();
            if (!mainKey.equals(key)) {
                result.append(" ").append(key).append("=").append(pair.getValue());
            }
        }
        if (isMainValueNull) {
            ConvertUtils.wrapWithAngleBrackets(result.append("/"));
        } else {
            ConvertUtils.wrapWithAngleBrackets(result);
            StringBuilder builder = new StringBuilder().append("/").append(mainKey);
            ConvertUtils.wrapWithAngleBrackets(builder);
            String mainValue = map.get(mainKey);
            result.append(mainValue.startsWith("\"") ? mainValue.substring(1, mainValue.length() - 1) : mainValue).append(builder);
        }
    }

    private static void convertXML(String input, StringBuilder result) {
        String mainAttr = findMainAttr(input);
        String mainValue = findMainValue(input);
        Pattern pattern = Pattern.compile("(\\w+)(\\s*=\\s*\")(.+?)(\")");
        Matcher matcher = pattern.matcher(input);
        final boolean hasAttr = matcher.find();
        var map = new LinkedHashMap<String, String>();
        if (hasAttr) {
            do {
                map.put(matcher.group(1), matcher.group(3));
            } while (matcher.find());
            for (var key : map.keySet()) {
                final String value = map.get(key);
                joinPair(result, "@" + key, value, ",");
            }
            joinPair(result, "#" + mainAttr, mainValue, "");
            constructBody(result, mainAttr);
            ConvertUtils.warpWithCurlyBrace(result);
        } else {
            joinPair(result, mainAttr, mainValue, "");
            ConvertUtils.warpWithCurlyBrace(result);
        }
    }

    private static boolean hasContent(String input) {
        return input.indexOf("/>") == -1;
    }

    private static String findMainValue(String input) {
        return hasContent(input) ? input.substring(input.indexOf(">") + 1, input.indexOf("</")) : "";
    }

    private static String findMainAttr(String input) {
        Pattern pattern = Pattern.compile("(<)(\\w+)(\\s|>|/)");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            return matcher.group(2);
        }
        return "";
    }

    private static void constructBody(StringBuilder result, String mainAttr) {
        result.insert(0, "\"" + mainAttr + "\":{").append("}");
    }

    public static void joinPair(StringBuilder result, String key, String value, String split) {
        if (value.equals("")) {
            result.append("\"").append(key).append("\":").append("null").append(split);
        } else {
            result.append("\"").append(key).append("\":\"").append(value).append("\"").append(split);
        }
    }
}
