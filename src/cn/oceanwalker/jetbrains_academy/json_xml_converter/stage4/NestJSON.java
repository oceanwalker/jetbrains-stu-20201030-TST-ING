package cn.oceanwalker.jetbrains_academy.json_xml_converter.stage4;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.ConvertUtils;
import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5.Element;
import cn.oceanwalker.utils.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NestJSON {
    public static void main(String[] args) {
        final String json = "{\n" +
                "    \"elem1\": {\n" +
                "        \"@attr1\": \"val1\",\n" +
                "        \"@attr2\": \"val2\",\n" +
                "        \"#elem1\": {\n" +
                "            \"elem2\": {\n" +
                "                \"@attr3\": \"val3\",\n" +
                "                \"@attr4\": \"val4\",\n" +
                "                \"#elem2\": \"Value1\"\n" +
                "            },\n" +
                "            \"elem3\": {\n" +
                "                \"@attr5\": \"val5\",\n" +
                "                \"@attr6\": \"val6\",\n" +
                "                \"#elem3\": \"Value2\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        System.out.println(nest(json));
    }

    static class JsonElement {
        String content;
        int endIndex;
    }

    public static String nest(String json) {
        json = json.replaceAll("\r\n", "\n");
        Element element = getElement(json);
        String result = ConvertUtils.printElement(element);
        result = result.replaceAll("\n", "\r\n");
        return result;
//        StringBuilder builder = new StringBuilder();
//        decodeJson(json, "", builder);
//        String result = builder.toString();
//        result = result.replaceAll("\n", "\r\n");
//        return Utils.removeLastLine(result);
    }

    public static Element getElement(String json) {
        json = json.replaceAll("\r\n", "\n");
        json = Utils.removeSpaceAndLine(json);
        Element root = null;
        if (getElementMap(json).size() > 1) {
            root = new Element("root");
        }
        return jsonToIntermediate(json, root);
    }

    public static Element jsonToIntermediate(String json, Element root) {
        List<Element> children = new ArrayList<>();
        Map<String, String> elementMap = getElementMap(json);
        for (Map.Entry<String, String> entry : elementMap.entrySet()) {
            String elementName = entry.getKey();
            if (isIllegalElementName(elementName)) {
                continue;
            }
            if (elementName.startsWith("#") || elementName.startsWith("@")) {
                elementName = elementName.substring(1);
            }
            Element element = new Element(elementName);
            String content = entry.getValue();
            if (isASingleValue(content)) {
                element.setValue(content);
            } else if (isAnObjectWithAttributes(elementName, content)) {
                buildJsonWithAttrToElement(element, content);
            } else {
                jsonToIntermediate(content, element);
            }
            children.add(element);
        }
        if (null != root) {
            root.setChildren(children);
        } else {
            root = children.get(0);
        }
        return root;
    }

    private static void buildJsonWithAttrToElement(Element element, String jsonWithAttr) {
        String value = "";
        Map<String, String> attrMap = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : getElementMap(jsonWithAttr).entrySet()) {
            String pairKey = entry.getKey();
            String pairValue = entry.getValue();
            if (("#" + element.getName()).equals(pairKey)) {
                value = pairValue;
            } else {
                if (pairKey.startsWith("@")) {
                    pairKey = pairKey.substring(1);
                }
                if (pairValue.equals("null")) {
                    pairValue = "\"\"";
                }
                attrMap.put(pairKey, pairValue);
            }
        }
        final boolean hasChild = value.startsWith("{");
        if (value.length() > 0 && !hasChild) {
            element.setValue(value);
        }

        if (attrMap.size() > 0) {
            element.setAttrMap(attrMap);
        }
        if (hasChild) {
            jsonToIntermediate(value, element);
        }
    }

    private static void decodeJson(String json, String path, StringBuilder result) {
        Map<String, String> elementMap = getElementMap(json);
        for (Map.Entry<String, String> entry : elementMap.entrySet()) {
            String elementName = entry.getKey();
            if (isIllegalElementName(elementName)) {
                continue;
            }
            if (elementName.startsWith("#") || elementName.startsWith("@")) {
                elementName = elementName.substring(1);
            }
            String content = entry.getValue();
            if (isASingleValue(content)) {
                nestingJsonWithJustValue(content, path, elementName, result);
            } else if (isAnObjectWithAttributes(elementName, content)) {
                nestingJsonWithAttr(content, path, elementName, result);
            } else {
                appendPath(path, elementName, "\n\n", result);
                decodeJson(content, ConvertUtils.getNewPath(path, elementName), result);
            }
        }
    }

    private static boolean isIllegalElementName(String elementName) {
        return "@".equals(elementName) || "#".equals(elementName) || elementName.length() < 1;
    }

    public static boolean isASingleValue(String content) {
        return isBlankCurlyBrace(content)
                || "null".equals(content)
                || content.startsWith("\"");
    }

    private static int getLegalElementCount(String content) {
        int result = 0;
        Map<String, String> elementMap = getElementMap(content);
        for (Map.Entry<String, String> entry : elementMap.entrySet()) {
            String elementName = entry.getKey();
            if (isIllegalElementName(elementName)) {
                continue;
            }
            result++;
        }
        return result;
    }

    private static boolean isNumber(String content) {
        return content.matches("\\d+.?\\d*");
    }

    private static boolean isBlankCurlyBrace(String content) {
        return content.matches("\\{\\s*}");
    }

    public static boolean isAnObjectWithAttributes(String key, String value) {
        //case 1
        boolean hasObjKey = false;
        //case 2
        boolean allAttrKeyRight = true;
        //case 3
        boolean hasNoCurlyBraceInAttrValue = true;
        Map<String, String> elementMap = getElementMap(value);
        for (Map.Entry<String, String> entry : elementMap.entrySet()) {
            String elementName = entry.getKey();
            String content = entry.getValue();
            if (("#" + key).equals(elementName)) {
                hasObjKey = true;
            } else {
                if (!elementName.startsWith("@") || elementName.length() <= 1) {
                    allAttrKeyRight = false;
                    break;
                }
                if (content.contains("{")) {
                    hasNoCurlyBraceInAttrValue = false;
                    break;
                }
            }
        }
        return hasObjKey && allAttrKeyRight && hasNoCurlyBraceInAttrValue;
    }

    private static Map<String, String> getElementMap(String json) {
        Map<String, String> result = new LinkedHashMap<>();
        Pattern startPattern = Pattern.compile("\"(.*?)\"\\s*:");
        Matcher startMatcher = startPattern.matcher(json);
        while (startMatcher.find()) {
            String[] split = startMatcher.group().split("\"");
            final String elementName = split[split.length - 2];
            int signMatchStart = startMatcher.end();

            JsonElement element = getContent(json, signMatchStart);
            String content = element.content.trim();

            if (isNumber(content)) {
                content = "\"" + content + "\"";
            }
            if (content.startsWith("{") && getLegalElementCount(content) == 0) {
                content = "\"\"";
            }
            result.put(elementName, content);
            if (content.startsWith("{")) {
                startMatcher.region(element.endIndex, json.length());
            } else if (content.startsWith("\"")) {
            }
        }
        removeRepeatElement(result);
        return result;
    }

    public static void removeRepeatElement(Map<String, String> result) {
        Iterator<Map.Entry<String, String>> iterator = result.entrySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next().getKey();
            if (key.startsWith("@") || key.startsWith("#")) {
                if (result.containsKey(key.substring(1))) {
                    iterator.remove();
                }
            }
        }
    }

    private static JsonElement getContent(String json, int signMatchStart) {
        JsonElement result = new JsonElement();
        Pattern signPattern = Pattern.compile("[\"{}n,\\d]");
        Matcher signMatcher = signPattern.matcher(json);
        signMatcher.region(signMatchStart, json.length());
        if (signMatcher.find()) {
            int valueStartIndex = signMatcher.start();
            if ("{".equals(signMatcher.group())) {
                final int curlyBraceMatcherStart = signMatcher.end();
                final int curlyBraceMatcherEnd = getCurlyBraceMatcherEnd(json, curlyBraceMatcherStart);
                String jsonWithAttr = json.substring(signMatchStart, curlyBraceMatcherEnd);
                result.content = jsonWithAttr;
                result.endIndex = curlyBraceMatcherEnd;
            } else if ("\"".equals(signMatcher.group())) {
                while (signMatcher.find()) {
                    if ("\"".equals(signMatcher.group())) {
                        break;
                    }
                }
                String value = json.substring(valueStartIndex, signMatcher.end());
                result.content = value;
                result.endIndex = signMatcher.end();
            } else {
                while (signMatcher.find()) {
                    if (",".equals(signMatcher.group()) || "}".equals(signMatcher.group())) {
                        break;
                    }
                }
                String value = json.substring(valueStartIndex, signMatcher.start());
                result.content = value;
                result.endIndex = signMatcher.end();
            }
        }
        result.content = null == result.content ? null : result.content.trim();
        return result;
    }

    private static int getCurlyBraceMatcherEnd(String json, int curlyBraceMatcherStart) {
        Pattern curlyBracePattern = Pattern.compile("[{}]");
        Matcher curlyBraceMatcher = curlyBracePattern.matcher(json);
        curlyBraceMatcher.region(curlyBraceMatcherStart, json.length());
        Deque<String> stack = new ArrayDeque<>();
        stack.offer("{");
        while (curlyBraceMatcher.find()) {
            final String sign = curlyBraceMatcher.group();
            if (sign.equals(stack.getLast())) {
                stack.offer(sign);
            } else {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                break;
            }
        }
        return curlyBraceMatcher.end();
    }

    private static void nestingJsonWithJustValue(String value, String path, String elementName, StringBuilder result) {
        appendPath(path, elementName, "\n", result);
        if (isBlankCurlyBrace(value)) {
            value = "\"\"";
        }
        result.append("value = ").append(value).append("\n").append("\n");
    }

    private static void nestingJsonWithAttr(String jsonWithAttr, String path, String elementName, StringBuilder result) {
        appendPath(path, elementName, "\n", result);
        String value = "";
        Map<String, String> attrMap = new LinkedHashMap<>();
        for (Map.Entry<String, String> entry : getElementMap(jsonWithAttr).entrySet()) {
            String pairKey = entry.getKey();
            String pairValue = entry.getValue();

            if (("#" + elementName).equals(pairKey)) {
                value = pairValue;
            } else {
                if (pairKey.startsWith("@")) {
                    pairKey = pairKey.substring(1);
                }
                if (pairValue.equals("null")) {
                    pairValue = "\"\"";
                }
                attrMap.put(pairKey, pairValue);
            }
        }
        final boolean hasChild = value.startsWith("{");
        if (value.length() > 0 && !hasChild) {
            result.append("value = ").append(value).append("\n");
        }

        if (attrMap.size() > 0) {
            result.append("attributes:\n");
            for (Map.Entry<String, String> entry : attrMap.entrySet()) {
                result.append(entry.getKey()).append(" = ").append(entry.getValue()).append("\n");
            }
        }
        result.append("\n");
        if (hasChild) {
            decodeJson(value, ConvertUtils.getNewPath(path, elementName), result);
        }
    }

    private static void appendPath(String path, String elementName, String tail, StringBuilder result) {
        result.append("Element:\n")
                .append("path = ").append(ConvertUtils.getNewPath(path, elementName)).append(tail);
    }

}
