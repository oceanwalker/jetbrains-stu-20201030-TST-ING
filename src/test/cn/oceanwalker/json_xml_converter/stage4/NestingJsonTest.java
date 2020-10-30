package test.cn.oceanwalker.json_xml_converter.stage4;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage4.NestJSON;
import cn.oceanwalker.utils.Utils;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author Eagle
 */
public class NestingJsonTest {
    @Test
    public void testAll() throws IOException {
        for (int i = 1; i < 4; i++) {
            TestCase(i);
        }
    }

    @Test
    public void test3() throws IOException {
        TestCase(3);
    }
    private void TestCase(int i) throws IOException {
        assertEquals(Utils.getFileContent(NestingJsonTest.class, "output" + i + ".txt"), NestJSON.nest(Utils.getFileContent(NestingJsonTest.class, "input" + i + ".txt")));
        System.out.println(i + " / 3 complete!");
    }

    @Test
    public void testRemoveRepeatElement() {
        Map<String, String> actualMap = new LinkedHashMap<>();
        actualMap.put("1", "1");
        actualMap.put("@1", "1");
        actualMap.put("2", "2");
        actualMap.put("#2", "2");

        Map<String, String> expectedMap = new LinkedHashMap<>();
        expectedMap.put("1", "1");
        expectedMap.put("2", "2");
        NestJSON.removeRepeatElement(actualMap);
        assertEquals(expectedMap, actualMap);
    }

    @Test
    public void testMatchNumber() {
        assertTrue("123.456".matches("\\d+.?\\d*"));
    }

    @Test
    public void test2() throws IOException {
        TestCase(2);
    }

    @Test
    public void test2Integrate() throws IOException {
        TestCase(2);
        test2InCopyMode();
    }

    @Test
    public void testIsAnObjectWithAttributes() {
        String[] trueNameArray = {
                //1
                "root1"
                //2
                , "root2"
                //3
                , "root1"
        };
        String[] trueContentArray = {
                //1
                "{\n" +
                        "        \"@attr1\": \"val1\",\n" +
                        "        \"@attr2\": \"val2\",\n" +
                        "        \"#root1\": {\n" +
                        "            \"elem1\": {\n" +
                        "                \"@attr3\": \"val3\",\n" +
                        "                \"@attr4\": \"val4\",\n" +
                        "                \"#elem1\": \"Value1\"\n" +
                        "            },\n" +
                        "            \"elem2\": {\n" +
                        "                \"@attr5\": \"val5\",\n" +
                        "                \"@attr6\": \"val6\",\n" +
                        "                \"#elem2\": \"Value2\"\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }"
                //2
                , "{\n" +
                "        \"@attr1\": null,\n" +
                "        \"@attr2\": \"\",\n" +
                "        \"#root2\": null\n" +
                "    }"
                //3
                , "{\"@attr1\":\"val1\",\"@attr2\":\"val2\",\"#root1\":{\"elem1\":{\"@attr3\":\"val3\",\"@attr4\":\"val4\",\"#elem1\":\"Value1\"},\"elem2\":{\"@attr5\":\"val5\",\"@attr6\":\"val6\",\"#elem2\":\"Value2\"}}}"
        };
        String[] falseNameArray = {
                //1
                "inner1"
                //2
                , "inner4"
        };
        String[] falseContentArray = {
                //1
                "{\n" +
                        "            \"inner2\": {\n" +
                        "                \"inner3\": {\n" +
                        "                    \"key1\": \"value1\",\n" +
                        "                    \"key2\": \"value2\"\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }"
                //2
                , "{\n" +
                "            \"@\": 123,\n" +
                "            \"#inner4\": \"value3\"\n" +
                "        }"
        };
        for (int i = 2; i < trueNameArray.length; i++) {
            assertTrue(NestJSON.isAnObjectWithAttributes(trueNameArray[i], trueContentArray[i]));
        }
        for (int i = 0; i < falseNameArray.length; i++) {
            assertFalse(NestJSON.isAnObjectWithAttributes(falseNameArray[i], falseContentArray[i]));
        }
    }

    @Test
    public void testRegex(){
        String regex="\"";
        String[] split1="\"val1\", \"@attr2\":".split(regex);
        assertEquals("@attr2",split1[split1.length-2]);
        String[] split2="\"@attr1\":".split(regex);
        assertEquals("@attr1",split2[split2.length-2]);
    }

    @Test
    public void test2InCopyMode() throws IOException {
        assertEquals("Element:\r\n" +
                "path = root1\r\n" +
                "attributes:\r\n" +
                "attr1 = \"val1\"\r\n" +
                "attr2 = \"val2\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root1, elem1\r\n" +
                "value = \"Value1\"\r\n" +
                "attributes:\r\n" +
                "attr3 = \"val3\"\r\n" +
                "attr4 = \"val4\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root1, elem2\r\n" +
                "value = \"Value2\"\r\n" +
                "attributes:\r\n" +
                "attr5 = \"val5\"\r\n" +
                "attr6 = \"val6\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root2\r\n" +
                "value = null\r\n" +
                "attributes:\r\n" +
                "attr1 = \"\"\r\n" +
                "attr2 = \"\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root3\r\n" +
                "value = \"\"\r\n" +
                "attributes:\r\n" +
                "attr1 = \"val2\"\r\n" +
                "attr2 = \"val1\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root4\r\n" +
                "value = \"Value4\"", NestJSON.nest("{\r\n" +
                "    \"root1\": {\r\n" +
                "        \"@attr1\": \"val1\",\r\n" +
                "        \"@attr2\": \"val2\",\r\n" +
                "        \"#root1\": {\r\n" +
                "            \"elem1\": {\r\n" +
                "                \"@attr3\": \"val3\",\r\n" +
                "                \"@attr4\": \"val4\",\r\n" +
                "                \"#elem1\": \"Value1\"\r\n" +
                "            },\r\n" +
                "            \"elem2\": {\r\n" +
                "                \"@attr5\": \"val5\",\r\n" +
                "                \"@attr6\": \"val6\",\r\n" +
                "                \"#elem2\": \"Value2\"\r\n" +
                "            }\r\n" +
                "        }\r\n" +
                "    },\r\n" +
                "    \"root2\": {\r\n" +
                "        \"@attr1\": null,\r\n" +
                "        \"@attr2\": \"\",\r\n" +
                "        \"#root2\": null\r\n" +
                "    },\r\n" +
                "    \"root3\": {\r\n" +
                "        \"@attr1\": \"val2\",\r\n" +
                "        \"@attr2\": \"val1\",\r\n" +
                "        \"#root3\": \"\"\r\n" +
                "    },\r\n" +
                "    \"root4\": \"Value4\"\r\n" +
                "}"));
    }

    @Test
    public void testElementWithChild() {
        testElementWithNoneAttr();
        assertEquals("Element:\r\n" +
                "path = root1\r\n" +
                "attributes:\r\n" +
                "attr1 = \"val1\"\r\n" +
                "attr2 = \"val2\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root1, elem1\r\n" +
                "value = \"Value1\"\r\n" +
                "attributes:\r\n" +
                "attr3 = \"val3\"\r\n" +
                "attr4 = \"val4\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root1, elem2\r\n" +
                "value = \"Value2\"\r\n" +
                "attributes:\r\n" +
                "attr5 = \"val5\"\r\n" +
                "attr6 = \"val6\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root2\r\n" +
                "value = null\r\n" +
                "attributes:\r\n" +
                "attr1 = \"\"\r\n" +
                "attr2 = \"\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root3\r\n" +
                "value = \"\"\r\n" +
                "attributes:\r\n" +
                "attr1 = \"val2\"\r\n" +
                "attr2 = \"val1\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root4\r\n" +
                "value = \"Value4\"", NestJSON.nest("{\r\n" +
                "    \"root1\": {\r\n" +
                "        \"@attr1\": \"val1\",\r\n" +
                "        \"@attr2\": \"val2\",\r\n" +
                "        \"#root1\": {\r\n" +
                "            \"elem1\": {\r\n" +
                "                \"@attr3\": \"val3\",\r\n" +
                "                \"@attr4\": \"val4\",\r\n" +
                "                \"#elem1\": \"Value1\"\r\n" +
                "            },\r\n" +
                "            \"elem2\": {\r\n" +
                "                \"@attr5\": \"val5\",\r\n" +
                "                \"@attr6\": \"val6\",\r\n" +
                "                \"#elem2\": \"Value2\"\r\n" +
                "            }\r\n" +
                "        }\r\n" +
                "    },\r\n" +
                "    \"root2\": {\r\n" +
                "        \"@attr1\": null,\r\n" +
                "        \"@attr2\": \"\",\r\n" +
                "        \"#root2\": null\r\n" +
                "    },\r\n" +
                "    \"root3\": {\r\n" +
                "        \"@attr1\": \"val2\",\r\n" +
                "        \"@attr2\": \"val1\",\r\n" +
                "        \"#root3\": \"\"\r\n" +
                "    },\r\n" +
                "    \"root4\": \"Value4\"\r\n" +
                "}"));
    }

    @Test
    public void testSingleElement() {
        assertEquals("Element:\r\n" +
                "path = root4\r\n" +
                "value = \"Value4\"", NestJSON.nest ("{\r\n" +
                "    \"root4\": \"Value4\"\r\n" +
                "}"));
    }

    @Test
    public void testElementWithAttr() {
        testSingleElement();
        assertEquals("Element:\r\n" +
                "path = root3\r\n" +
                "value = \"\"\r\n" +
                "attributes:\r\n" +
                "attr1 = \"val2\"\r\n" +
                "attr2 = \"val1\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root4\r\n" +
                "value = \"Value4\"", NestJSON.nest("{\r\n" +
                "\t\"root3\": {\r\n" +
                "        \"@attr1\": \"val2\",\r\n" +
                "        \"@attr2\": \"val1\",\r\n" +
                "        \"#root3\": \"\"\r\n" +
                "    },\r\n" +
                "    \"root4\": \"Value4\"\r\n" +
                "}"));
    }

    @Test
    public void testElementWithNoneAttr() {
        testElementWithAttr();
        assertEquals("Element:\r\n" +
                "path = root2\r\n" +
                "value = null\r\n" +
                "attributes:\r\n" +
                "attr1 = \"\"\r\n" +
                "attr2 = \"\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root3\r\n" +
                "value = \"\"\r\n" +
                "attributes:\r\n" +
                "attr1 = \"val2\"\r\n" +
                "attr2 = \"val1\"\r\n" +
                "\r\n" +
                "Element:\r\n" +
                "path = root4\r\n" +
                "value = \"Value4\"", NestJSON.nest("{\r\n" +
                "    \"root2\": {\r\n" +
                "        \"@attr1\": null,\r\n" +
                "        \"@attr2\": \"\",\r\n" +
                "        \"#root2\": null\r\n" +
                "    },\"root3\": {\r\n" +
                "        \"@attr1\": \"val2\",\r\n" +
                "        \"@attr2\": \"val1\",\r\n" +
                "        \"#root3\": \"\"\r\n" +
                "    },\r\n" +
                "    \"root4\": \"Value4\"\r\n" +
                "}"));
    }
}
