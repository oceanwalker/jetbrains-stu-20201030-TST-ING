package cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NestXml {
    public static Element getElement(String input) {
        input = ConvertUtils.removeSpaceBetweenAngleBrackets(input);
        input = ConvertUtils.removeLine(input);
        return xmlToIntermediate(input, null);
    }

    private static Element xmlToIntermediate(String input, Element element) {
        List<Element> children = new ArrayList<>();
        List<String> tagList = findTagList(input);
        for (String tag : tagList) {
            if (hasXmlChildren(tag)) {
                String parentXml = getXmlParent(tag);
                String childrenXml = getXmlChildren(tag);
                Element fatherElement = buildeXmlToElement(parentXml, true);
                xmlToIntermediate(childrenXml, fatherElement);
                children.add(fatherElement);
            } else {
                Element singleElement = buildeXmlToElement(tag, false);
                children.add(singleElement);
            }
        }
        if (null == element) {
            return children.get(0);
        } else {
            element.setChildren(children);
        }
        return element;
    }

    public static Element buildeXmlToElement(String content, boolean hasChild) {
        //name
        String name = findElementName(content);
        Element element = new Element(name);
        //value
        if (!hasChild) {
            element.setValue(hasValue(content) ? "\"" + findValue(content) + "\"" : "null");
        }
        //attr
        LinkedHashMap<String, String> map = getAttributeMap(content);
        if (map.size() > 0) {
            element.setAttrMap(map);
        }
        return element;
    }

    public static String getXmlChildren(String tag) {
        tag = tag.substring(tag.indexOf(">") + 1);
        tag = tag.substring(0, tag.lastIndexOf("</"));
        return tag;
    }

    public static List<String> findTagList(String input) {
        List<String> result = new ArrayList<>();
        while (input.length() > 0) {
            String name = findElementName(input);
            final String noValueSuffix = "/>";
            final String hasValueSuffix = "/" + name + ">";
            final int noValueSuffixIndex = input.indexOf(noValueSuffix);
            final int hasValueSuffixIndex = input.indexOf(hasValueSuffix);
            final boolean hasChild = hasXmlChildren(input);
            final boolean isEntireContent = hasValueSuffixIndex == input.length() - hasValueSuffix.length();
            if ((!hasChild || isEntireContent) && input.lastIndexOf("<" + name) == input.indexOf("<" + name)) {
                result.add(input);
                break;
            } else {
                int indexOfFirstEnd = 0;
                final boolean isOnlyFindHasValueSuffix = hasValueSuffixIndex > 0 && noValueSuffixIndex < 0;
                final boolean isBothFindAndHasValueSuffixBefore = hasValueSuffixIndex > 0 && noValueSuffixIndex > 0 && hasValueSuffixIndex < noValueSuffixIndex;
                if (isOnlyFindHasValueSuffix || isBothFindAndHasValueSuffixBefore) {
                    indexOfFirstEnd = hasValueSuffixIndex + 2 + name.length();
                } else {
                    indexOfFirstEnd = noValueSuffixIndex + 2;
                }
                result.add(input.substring(0, indexOfFirstEnd));
                input = input.substring(indexOfFirstEnd);
            }
        }
        return result;
    }

    public static String getXmlParent(String tag) {
        return tag.substring(0, tag.indexOf(">") + 1);
    }

    private static LinkedHashMap<String, String> getAttributeMap(String tag) {
        Pattern pattern = Pattern.compile("(\\w+)(\\s*=\\s*)(\".+?\")");
        Matcher matcher = pattern.matcher(tag);
        var map = new LinkedHashMap<String, String>();
        while (matcher.find()) {
            map.put(matcher.group(1), matcher.group(3));
        }
        return map;
    }

    public static boolean hasXmlChildren(String tag) {
        return tag.matches("^.*>.*<.*<.*$");
    }

    public static String findElementName(String input) {
        Pattern pattern = Pattern.compile("(<)([\\w\\d]+)(\\s|>|/)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(2);
        }
        return "";
    }

    public static String findValue(String input) {
        return hasValue(input) ? input.substring(input.indexOf(">") + 1, input.indexOf("</")) : "";
    }

    private static boolean hasValue(String input) {
        return !input.contains("/>");
    }
}
