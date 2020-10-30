package test.cn.oceanwalker.json_xml_converter.stage5;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5.AdvancedConverter;
import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5.Element;
import cn.oceanwalker.utils.Utils;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JsonToIntermediateToXmlTest {
    String expected;
    static Element elem1 = new Element("elem1");
    static Element elem2 = new Element("elem2");
    static Element elem3 = new Element("elem3");

    @Test
    public void test() {
        Map<String, String> attrMap;
        attrMap = new LinkedHashMap<>();
        attrMap.put("attr1", "\"val1\"");
        attrMap.put("attr2", "\"val2\"");
        elem1.setAttrMap(attrMap);

        elem2.setValue("\"Value1\"");
        attrMap = new LinkedHashMap<>();
        attrMap.put("attr3", "\"val3\"");
        attrMap.put("attr4", "\"val4\"");
        elem2.setAttrMap(attrMap);

        elem3.setValue("\"Value2\"");
        attrMap = new LinkedHashMap<>();
        attrMap.put("attr5", "\"val5\"");
        attrMap.put("attr6", "\"val6\"");
        elem3.setAttrMap(attrMap);

        List<Element> listOfElem1 = List.of(elem2, elem3);
        elem1.setChildren(listOfElem1);

        expected = "<elem1 attr1=\"val1\" attr2=\"val2\">\n" +
                "    <elem2 attr3=\"val3\" attr4=\"val4\">Value1</elem2>\n" +
                "    <elem3 attr5=\"val5\" attr6=\"val6\">Value2</elem3>\n" +
                "</elem1>";
        assertEquals(
                Utils.removeSpace(expected),
                Utils.removeSpace(AdvancedConverter.intermediateToXML(elem1)));
    }
}
