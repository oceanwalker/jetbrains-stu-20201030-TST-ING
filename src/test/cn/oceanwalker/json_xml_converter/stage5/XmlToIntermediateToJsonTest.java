package test.cn.oceanwalker.json_xml_converter.stage5;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5.AdvancedConverter;
import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage5.Element;
import cn.oceanwalker.utils.Utils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class XmlToIntermediateToJsonTest {
    String expected;
    static Element root = new Element("root");
    static Element id = new Element("id");
    static Element number = new Element("number");
    static Element nonattr1 = new Element("nonattr1");
    static Element nonattr2 = new Element("nonattr2");
    static Element nonattr3 = new Element("nonattr3");
    static Element attr1 = new Element("attr1");
    static Element attr2 = new Element("attr2");
    static Element attr3 = new Element("attr3");
    static Element email = new Element("email");
    static Element to = new Element("to");
    static Element from = new Element("from");
    static Element subject = new Element("subject");
    static Element body = new Element("body");
    static Element date = new Element("date");

    @BeforeClass
    public static void initElement() {
        Map<String, String> attrMap;

        id.setValue("\"6753322\"");

        attrMap = new LinkedHashMap<>();
        attrMap.put("region", "\"Russia\"");
        number.setAttrMap(attrMap);
        number.setValue("\"8-900-000-00-00\"");

        nonattr1.setValue("null");

        nonattr2.setValue("\"\"");

        nonattr3.setValue("\"text\"");

        attr1.setValue("null");
        attrMap = new LinkedHashMap<>();
        attrMap.put("id", "\"1\"");
        attr1.setAttrMap(attrMap);

        attr2.setValue("\"\"");
        attrMap = new LinkedHashMap<>();
        attrMap.put("id", "\"2\"");
        attr2.setAttrMap(attrMap);

        attr3.setValue("\"text\"");
        attrMap = new LinkedHashMap<>();
        attrMap.put("id", "\"3\"");
        attr3.setAttrMap(attrMap);

        to.setValue("\"to_example@gmail.com\"");

        from.setValue("\"from_example@gmail.com\"");

        subject.setValue("\"Project discussion\"");

        body.setValue("\"Body message\"");
        attrMap = new LinkedHashMap<>();
        attrMap.put("font", "\"Verdana\"");
        body.setAttrMap(attrMap);

        date.setValue("null");
        attrMap = new LinkedHashMap<>();
        attrMap.put("day", "\"12\"");
        attrMap.put("month", "\"12\"");
        attrMap.put("year", "\"2018\"");
        date.setAttrMap(attrMap);

        List<Element> childrenOfEmail = List.of(to, from, subject, body, date);
        email.setChildren(childrenOfEmail);

        List<Element> childrenOfRoot = List.of(id, number, nonattr1, nonattr2, nonattr3, attr1, attr2, attr3, email);
        root.setChildren(childrenOfRoot);
    }

    @Test
    public void testRootWithChildren() {
        expected = "{\n" +
                "    \"root\": {\n" +
                "        \"id\": \"6753322\",\n" +
                "        \"number\": {\n" +
                "            \"@region\": \"Russia\",\n" +
                "            \"#number\": \"8-900-000-00-00\"\n" +
                "        },\n" +
                "        \"nonattr1\": null,\n" +
                "        \"nonattr2\": \"\",\n" +
                "        \"nonattr3\": \"text\",\n" +
                "        \"attr1\": {\n" +
                "            \"@id\": \"1\",\n" +
                "            \"#attr1\": null\n" +
                "        },\n" +
                "        \"attr2\": {\n" +
                "            \"@id\": \"2\",\n" +
                "            \"#attr2\": \"\"\n" +
                "        },\n" +
                "        \"attr3\": {\n" +
                "            \"@id\": \"3\",\n" +
                "            \"#attr3\": \"text\"\n" +
                "        },\n" +
                "        \"email\": {\n" +
                "            \"to\": \"to_example@gmail.com\",\n" +
                "            \"from\": \"from_example@gmail.com\",\n" +
                "            \"subject\": \"Project discussion\",\n" +
                "            \"body\": {\n" +
                "                \"@font\": \"Verdana\",\n" +
                "                \"#body\": \"Body message\"\n" +
                "            },\n" +
                "            \"date\": {\n" +
                "                \"@day\": \"12\",\n" +
                "                \"@month\": \"12\",\n" +
                "                \"@year\": \"2018\",\n" +
                "                \"#date\": null\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";


        String actual = AdvancedConverter.intermediateToJSON(root);
        assertEquals(Utils.removeSpaceAndLineInJSON(expected), Utils.removeSpaceAndLineInJSON(actual));
    }
}
