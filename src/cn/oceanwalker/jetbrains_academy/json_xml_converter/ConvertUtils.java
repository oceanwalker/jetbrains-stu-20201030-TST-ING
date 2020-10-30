package cn.oceanwalker.jetbrains_academy.json_xml_converter;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5.Element;

import java.util.Map;

public class ConvertUtils {

    public static void joinPair(StringBuilder result, String key, String value) {
//        if (value.equals("")) {
//            result.append("\"").append(key).append("\":").append("null").append(split);
//        } else {
//        }
        result.append("\"").append(key).append("\": ").append(value);
    }

    public static void warpWithCurlyBrace(StringBuilder result) {
        result.insert(0, "{").append("}");
    }

    public static void wrapWithAngleBrackets(StringBuilder result) {
        result.insert(0, "<").append(">");
    }

    public static String removeDoubleQuotesIfExist(String value) {
        return value.startsWith("\"") ? value.substring(1, value.length() - 1) : value;
    }

    public static String getNewPath(String path, String elementName) {
        return "".equals(path) ? elementName : path + ", " + elementName;
    }

    public static String printElement(Element element) {
        StringBuilder result = new StringBuilder();
        appendElement(element, "", result);
        return result.toString();
    }

    private static void appendElement(Element element, String path, StringBuilder result) {
        if (result.length() > 0) {
            result.append("\n\n");
        }
        result.append("Element:\n");
        path = getNewPath(path, element.getName());
        result.append("path = " + path);
        if (null != element.getValue()) {
            result.append("\nvalue = ").append(element.getValue());
        }
        if (element.hasAttr()) {
            result.append("\n").append("attributes:");
            for (Map.Entry<String, String> entry : element.getAttrMap().entrySet()) {
                result.append("\n").append(entry.getKey()).append(" = ").append(entry.getValue());
            }
        }
        if (element.hasChildren()) {
            for (Element child : element.getChildren()) {
                appendElement(child, path, result);
            }
        }
    }
}
