package test.cn.oceanwalker.json_xml_converter.stage6;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage6.Element;
import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage6.NestXml;
import cn.oceanwalker.utils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cn.oceanwalker.jetbrains_academy.json_xml_converter.stage6.AdvancedConverter.intermediateToJSON;
import static org.junit.Assert.assertEquals;

/**
 * @author Eagle
 */
public class XmlToIntermediateToJsonTest {
    String expectedJson;
    String actualJson;
    String inputXml;
    Element actualElement;
    Element expectedElement;
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
    static Map<String, String> attrMap;

    @BeforeClass
    public static void initElement() {
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
    }

    @Before
    public void before() {
        expectedJson = "";
        actualJson = "";
        inputXml = "";
    }

    @Test
    public void testArray() {
        inputXml = "<?xml version = \"1.0\" encoding = \"utf-8\"?>\n" +
                "<transactions>\n" +
                "    <transaction>\n" +
                "        <id>6753322</id>\n" +
                "    </transaction>\n" +
                "    <transaction>\n" +
                "        <id>67533244</id>\n" +
                "    </transaction>\n" +
                "    <transaction>\n" +
                "        <id>67533257</id>\n" +
                "    </transaction>\n" +
                "</transactions>";
        Element transaction1 = new Element("transaction");
        transaction1.setChildren(List.of(id));
        Element transaction2 = new Element("transaction");
        Element id1 = new Element("id");
        id1.setValue("\"67533244\"");
        transaction2.setChildren(List.of(id1));
        Element transaction3 = new Element("transaction");
        Element id2 = new Element("id");
        id2.setValue("\"67533257\"");
        transaction3.setChildren(List.of(id2));
        Element transactions = new Element("transactions");
        transactions.setArray(List.of(transaction1, transaction2, transaction3));
        expectedElement = transactions;
        expectedJson = "{\n" +
                "    \"transactions\" : [\n" +
                "        {\n" +
                "            \"id\" : \"6753322\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\" : \"67533244\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"id\" : \"67533257\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        actualJson = intermediateToJSON(transactions);
    }

    @Test
    public void testFindTagListMuiltyNullElement() {
        assertEquals(2, NestXml.findTagList("<element /><element />").size());
    }

    @Test
    public void testFindTagListWithChildren() {
        assertEquals(1, NestXml.findTagList("<element> <deep deepattr=\"deepvalue\"> <element>1</element> <element>2</element> <element>3</element> </deep> </element>").size());
    }

    @Test
    public void testFindTagListWithMoreChildren() {
        assertEquals(8, NestXml.findTagList("<element></element><element /><element>123</element><element>123.456</element><element><key1>value1</key1><key2 attr=\"value2\">value3</key2></element><element attr2='value4'>value5</element><element><attr3>value4</attr3><elem>value5</elem></element><element><deep deepattr=\"deepvalue\"><element>1</element><element>2</element><element>3</element></deep></element>").size());
    }

    @Test
    public void testNullArray() {
        inputXml = "<array1>\n" +
                "            <element />\n" +
                "            <element />\n" +
                "        </array1>";
        Element array1 = new Element("array1");
        Element null1 = new Element("element");
        null1.setValue("null");
        Element null2 = new Element("element");
        null2.setValue("null");
        array1.setArray(List.of(null1, null2));

        expectedElement = array1;

        expectedJson = "{\"array1\": [\n" +
                "                null, null\n" +
                "            ]}";
        actualJson = intermediateToJSON(array1);
    }

    @Test
    public void testSingleQuote() {
        inputXml = "<number region='Russia'>8-900-000-00-00</number>";
        expectedElement = number;
    }

    @Test
    public void testRootWithChildren() {
        List<Element> childrenOfRoot = List.of(id, number, nonattr1, nonattr2, nonattr3, attr1, attr2, attr3, email);
        root.setChildren(childrenOfRoot);
        expectedJson = "{\n" +
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


        actualJson = intermediateToJSON(root);
    }

    @After
    public void judgeEquals() {
        if (!"".equals(inputXml)) {
            actualElement = NestXml.getElement(inputXml);
            assertEquals(expectedElement, actualElement);
        }
        assertEquals(Utils.removeSpaceAndLine(expectedJson), Utils.removeSpaceAndLine(actualJson));
    }
}
