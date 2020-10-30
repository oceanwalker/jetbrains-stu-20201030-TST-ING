package cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5;

import java.util.List;
import java.util.Map;

import static cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5.ConvertUtils.*;

public class AdvancedConverter {
    public static String convert(String content) {
        if (content.startsWith("{")) {
            return intermediateToXML(NestJson.getElement(content));
        } else {
            return intermediateToJSON(NestXml.getElement(content));
        }
    }

    public static String intermediateToJSON(Element element) {
        return assemblyJsonBody(element, true);
    }

    private static String assemblyJsonBody(Element element, boolean needWrap) {
        StringBuilder body = new StringBuilder();
        if (element.hasAttr()) {
            for (Map.Entry<String, String> entry : element.getAttrMap().entrySet()) {
                joinPair(body, "@" + entry.getKey(), entry.getValue());
                body.append(",");
            }
        }
        if (element.hasChildren()) {
            StringBuilder childBuilder = new StringBuilder();
            final List<Element> children = element.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Element child = children.get(i);
                childBuilder.append(assemblyJsonBody(child, false));
                if (i != children.size() - 1) {
                    childBuilder.append(",");
                }
            }
            if (element.hasAttr()) {
                warpWithCurlyBrace(childBuilder);
                joinPair(body, "#" + element.getName(), childBuilder.toString());
            } else {
                body.append(childBuilder);
            }
        } else if (element.hasValue()) {
            if (element.hasAttr()) {
                joinPair(body, "#" + element.getName(), element.getValue());
            } else {
                body.append(element.getValue());
            }
        }
        if (element.hasAttr() && !element.hasValue() && !element.hasChildren()) {
            body.deleteCharAt(body.length() - 1);
        }
        StringBuilder result = new StringBuilder();
        if (element.hasAttr() || element.hasChildren()) {
            warpWithCurlyBrace(body);
            joinPair(result, element.getName(), body.toString());
            if (needWrap) {
                warpWithCurlyBrace(result);
            }
        } else {
            joinPair(result, element.getName(), body.toString());
        }
        return result.toString();
    }

    public static String intermediateToXML(Element element) {
        StringBuilder result = new StringBuilder();
        //head
        result.append(element.getName());
        if (element.hasAttr()) {
            for (var pair : element.getAttrMap().entrySet()) {
                result.append(" ").append(pair.getKey()).append("=").append(pair.getValue());
            }
        }
        //tail
        StringBuilder tailBuilder = new StringBuilder().append("/").append(element.getName());
        wrapWithAngleBrackets(tailBuilder);
        //middle
        if (!element.hasChildren() && null == element.getValue()) {
            wrapWithAngleBrackets(result.append(" ").append("/"));
        } else {
            if (element.hasChildren()) {
                wrapWithAngleBrackets(result);
                final List<Element> children = element.getChildren();
                for (int i = 0; i < children.size(); i++) {
                    result.append(intermediateToXML(children.get(i)));
                }
                result.append(tailBuilder);
            } else {
                if (null != element.getValue() && !"null".equals(element.getValue())) {
                    wrapWithAngleBrackets(result);
                    result.append(removeDoubleQuotesIfExist(element.getValue()));
                    result.append(tailBuilder);
                } else {
                    result.append("/");
                    wrapWithAngleBrackets(result);
                }
            }
        }
        return result.toString();
    }
}