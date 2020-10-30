package test.cn.oceanwalker.json_xml_converter.stage6;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage6.AdvancedConverter;
import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage6.ConvertUtils;
import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage6.Element;
import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage6.NestJson;
import cn.oceanwalker.utils.Utils;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JsonToIntermediateToXmlTest {
    String inputJson;
    Element expectedElement;
    String expectedXml;
    static Element elem1 = new Element("elem1");
    static Element elem2 = new Element("elem2");
    static Element elem3 = new Element("elem3");
    static Map<String, String> attrMap;

    @Before
    public void before() {
        inputJson = "";
        expectedXml = "";
    }

    @Test
    public void testArrayWithNullAndSubArray() {
        inputJson = "\"data\": [\n" +
                "            123,\n" +
                "            true,\n" +
                "            false,\n" +
                "            [ ],\n" +
                "            [],\n" +
                "            { },\n" +
                "            {}    ,\n" +
                "            [\n" +
                "                1, 2, 3,\n" +
                "                {\n" +
                "                    \"@attr\": \"value6\",\n" +
                "                    \"#element\": \"value7\"\n" +
                "                }\n" +
                "            ],\n" +
                "            null,\n" +
                "            \"\"\n" +
                "        ]";

        Element data = new Element("data");
        Element element0 = new Element("");
        element0.setValue("123");
        Element element1 = new Element("");
        element1.setValue("true");
        Element element2 = new Element("");
        element2.setValue("false");
        Element element3 = new Element("");
        element3.setValue("");
        Element element4 = new Element("");
        element4.setValue("");
        Element element5 = new Element("");
        element5.setValue("");
        Element element6 = new Element("");
        element6.setValue("");
        Element element8 = new Element("");
        Element element9 = new Element("");
        element9.setValue("1");
        Element element10 = new Element("");
        element10.setValue("2");
        Element element11 = new Element("");
        element11.setValue("3");
        Element element12 = new Element("");
        attrMap = new LinkedHashMap<>();
        attrMap.put("attr", "\"value6\"");
        element12.setValue("\"value7\"");
        element12.setAttrMap(attrMap);
        element8.setArray(List.of(element9, element10, element11, element12));
        Element element13 = new Element("");
        Element element14 = new Element("");
        element14.setValue("");
        data.setArray(List.of(element0, element1, element2, element3, element4, element5, element6, element8, element13, element14));
        expectedElement = data;

        expectedXml = "<data>\n" +
                "        <element>123</element>\n" +
                "        <element>true</element>\n" +
                "        <element>false</element>\n" +
                "        <element></element>\n" +
                "        <element></element>\n" +
                "        <element></element>\n" +
                "        <element></element>\n" +
                "        <element>\n" +
                "            <element>1</element>\n" +
                "            <element>2</element>\n" +
                "            <element>3</element>\n" +
                "            <element attr=\"value6\">value7</element>\n" +
                "        </element>\n" +
                "        <element />\n" +
                "        <element></element>\n" +
                "    </data>";


        if (!"".equals(inputJson)) {
            Element actualElement = NestJson.getElement(inputJson);
            assertEquals(expectedElement, actualElement);
        }
        System.out.println("input to element success");
        if (!"".equals(expectedXml)) {
            String actualXml = AdvancedConverter.intermediateToXML(expectedElement);
            assertEquals(
                    Utils.removeSpace(expectedXml),
                    Utils.removeSpace(actualXml));
        }
    }

    @Test
    public void testChildren() {
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

        expectedXml = "<elem1 attr1=\"val1\" attr2=\"val2\">\n" +
                "    <elem2 attr3=\"val3\" attr4=\"val4\">Value1</elem2>\n" +
                "    <elem3 attr5=\"val5\" attr6=\"val6\">Value2</elem3>\n" +
                "</elem1>";
        expectedElement = elem1;


        if (!"".equals(inputJson)) {
            Element actualElement = NestJson.getElement(inputJson);
            assertEquals(expectedElement, actualElement);
        }
        System.out.println();
        if (!"".equals(expectedXml)) {
            String actualXml = AdvancedConverter.intermediateToXML(expectedElement);
            assertEquals(
                    Utils.removeSpace(expectedXml),
                    Utils.removeSpace(actualXml));
        }
    }

    @Test
    public void testGetArrayElementMap() {
        String arrayContent = "\n" +
                "            123,\n" +
                "            true,\n" +
                "            false,\n" +
                "            [ ],\n" +
                "            [],\n" +
                "            { },\n" +
                "            {},\n" +
                "            [\n" +
                "                1, 2, 3,\n" +
                "                {\n" +
                "                    \"@attr\": \"value6\",\n" +
                "                    \"#element\": \"value7\"\n" +
                "                }\n" +
                "            ],\n" +
                "            null,\n" +
                "            \"\",\n" +
                "            {\n" +
                "                \"key1\": \"value1\",\n" +
                "                \"key2\": {\n" +
                "                    \"@attr\": \"value2\",\n" +
                "                    \"#key2\": \"value3\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"@attr2\": \"value4\",\n" +
                "                \"#element\": \"value5\"\n" +
                "            }\n" +
                "        ";
        arrayContent = ConvertUtils.removeSpaceAndLine(arrayContent);
        List<String> array = NestJson.getArrayElementMap(arrayContent);
        assertEquals(12, array.size());
    }
}
