package cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Element {
    private String value;
    private String name;
    private Map<String, String> attrMap;
    private List<Element> children;

    public Element(String name) {
        this.name = name;
        attrMap = new LinkedHashMap<>();
        children = new ArrayList<>();
    }

    public Map<String, String> getAttrMap() {
        return attrMap;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setAttrMap(Map<String, String> attrMap) {
        this.attrMap = attrMap;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setChildren(List<Element> children) {
        this.children = children;
    }

    public boolean hasChildren() {
        return children.size() > 0;
    }

    public List<Element> getChildren() {
        return children;
    }

    public boolean hasAttr() {
        return attrMap.size() > 0;
    }

    public boolean hasValue() {
        return null != value;
    }
}
