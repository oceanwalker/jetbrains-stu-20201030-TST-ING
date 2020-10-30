package test.cn.oceanwalker.json_xml_converter.stage3;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage3.NestXML;
import cn.oceanwalker.utils.Utils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cn.oceanwalker.jetbrains_academy.json_xml_converter.stage3.NestXML.findElementName;
import static org.junit.Assert.*;

public class NestingXmlTest {
    @Test
    public void testGetXmlChildren() {
        final String[] input = {
                //1
                "<transaction>\n" +
                        "    <id>6753322</id>\n" +
                        "    <number region=\"Russia\">8-900-000-00-00</number>\n" +
                        "    <nonattr />\n" +
                        "    <nonattr></nonattr>\n" +
                        "    <nonattr>text</nonattr>\n" +
                        "    <attr id=\"1\" />\n" +
                        "    <attr id=\"2\"></attr>\n" +
                        "    <attr id=\"3\">text</attr>\n" +
                        "    <email>\n" +
                        "        <to>to_example@gmail.com</to>\n" +
                        "        <from>from_example@gmail.com</from>\n" +
                        "        <subject>Project discussion</subject>\n" +
                        "        <body font=\"Verdana\">Body message</body>\n" +
                        "        <date day=\"12\" month=\"12\" year=\"2018\"/>\n" +
                        "    </email>\n" +
                        "</transaction>"
                //2
                //3
        };
        final String[] expected = {//1
                "\n" +
                        "    <id>6753322</id>\n" +
                        "    <number region=\"Russia\">8-900-000-00-00</number>\n" +
                        "    <nonattr />\n" +
                        "    <nonattr></nonattr>\n" +
                        "    <nonattr>text</nonattr>\n" +
                        "    <attr id=\"1\" />\n" +
                        "    <attr id=\"2\"></attr>\n" +
                        "    <attr id=\"3\">text</attr>\n" +
                        "    <email>\n" +
                        "        <to>to_example@gmail.com</to>\n" +
                        "        <from>from_example@gmail.com</from>\n" +
                        "        <subject>Project discussion</subject>\n" +
                        "        <body font=\"Verdana\">Body message</body>\n" +
                        "        <date day=\"12\" month=\"12\" year=\"2018\"/>\n" +
                        "    </email>\n"
                //2
                //3};
        };
        for (int i = 0; i < input.length; i++) {
            assertEquals(expected[i], NestXML.getXmlChildren(input[i]));
        }
    }

    @Test
    public void testGetParent() {
        final String[] input = {
                //1
                inputArray[0]
                //2
                , "<email>\n" +
                "        <to>to_example@gmail.com</to>\n" +
                "        <from>from_example@gmail.com</from>\n" +
                "        <subject>Project discussion</subject>\n" +
                "        <body font=\"Verdana\">Body message</body>\n" +
                "        <date day=\"12\" month=\"12\" year=\"2018\"/>\n" +
                "    </email>"
                //3
                , "<child name = \"child_name2\" type = \"child_type2\">\n" +
                "        <subchild id = \"2\" auth=\"auth1\">Value2</subchild>\n" +
                "        <subchild id = \"3\" auth=\"auth2\">Value3</subchild>\n" +
                "        <subchild id = \"4\" auth=\"auth3\"></subchild>\n" +
                "        <subchild id = \"5\" auth=\"auth3\"/>\n" +
                "    </child>"
        };
        final String[] expected = {
                //1
                "<transaction>"
                //2
                , "<email>"
                //3
                , "<child name = \"child_name2\" type = \"child_type2\">"
        };
        for (int i = 0; i < input.length; i++) {
            assertEquals("input=\n" + input[i], expected[i], NestXML.getXmlParent(input[i]));
        }
    }

    @Test
    public void testBuildeNestingXml() {
        //1
        final String[] input = {
                //1
                "<transaction></transaction>",
                //2
                "<number region=\"Russia\">8-900-000-00-00</number>"
                //3
                , "<nonattr />"
                //4
                , "<nonattr></nonattr>"
        };
        final boolean[] hasChildren = {
                //
                true
                //
                , false
                //
                , false
                //
                , false
        };
        final String[] expected = {
                //1
                "Element:\n" + "path = transaction",
                //2
                "Element:\n" +
                        "path = transaction, number\n" +
                        "value = \"8-900-000-00-00\"\n" +
                        "attributes:\n" +
                        "region = \"Russia\""
                //3
                , "Element:\n" +
                "path = transaction, nonattr\n" +
                "value = null"
                //4
                , "Element:\n" +
                "path = transaction, nonattr\n" +
                "value = \"\""
        };
        final String[] oldPath = {
                //1
                "",
                //2
                "transaction"
                //3
                , "transaction"
                //4
                , "transaction"
        };
        for (int i = 0; i < input.length; i++) {
            validateBuildeNestringXml(input[i], oldPath[i], hasChildren[i], expected[i]);
        }
    }

    private void validateBuildeNestringXml(String input, String oldPath, boolean hasChild, String expected) {
        StringBuilder builder = new StringBuilder();
        NestXML.buildeNestingXml(input, hasChild, oldPath, builder);
        assertEquals("\nexpected=\n" + expected + "\n\nactual=\n" + builder.toString(), expected, builder.toString());
    }

    @Test
    public void testFindTag() {
        List<List<String>> expected = new ArrayList<>();
        //1
        expected.add(Arrays.asList("<transaction>\n" +
                "    <id>6753322</id>\n" +
                "    <number region=\"Russia\">8-900-000-00-00</number>\n" +
                "    <nonattr />\n" +
                "    <nonattr></nonattr>\n" +
                "    <nonattr>text</nonattr>\n" +
                "    <attr id=\"1\" />\n" +
                "    <attr id=\"2\"></attr>\n" +
                "    <attr id=\"3\">text</attr>\n" +
                "    <email>\n" +
                "        <to>to_example@gmail.com</to>\n" +
                "        <from>from_example@gmail.com</from>\n" +
                "        <subject>Project discussion</subject>\n" +
                "        <body font=\"Verdana\">Body message</body>\n" +
                "        <date day=\"12\" month=\"12\" year=\"2018\"/>\n" +
                "    </email>\n" +
                "</transaction>"));
        //2
        expected.add(Arrays.asList("<id>6753322</id>",
                "    <number region=\"Russia\">8-900-000-00-00</number>",
                "    <nonattr />",
                "    <nonattr></nonattr>",
                "    <nonattr>text</nonattr>",
                "    <attr id=\"1\" />",
                "    <attr id=\"2\"></attr>",
                "    <attr id=\"3\">text</attr>",
                "    <email>\n" +
                        "        <to>to_example@gmail.com</to>\n" +
                        "        <from>from_example@gmail.com</from>\n" +
                        "        <subject>Project discussion</subject>\n" +
                        "        <body font=\"Verdana\">Body message</body>\n" +
                        "        <date day=\"12\" month=\"12\" year=\"2018\"/>\n" +
                        "    </email>"));
        //3
        expected.add(Arrays.asList("<to>to_example@gmail.com</to>", "<from>from_example@gmail.com</from>", "<subject>Project discussion</subject>", "<body font=\"Verdana\">Body message</body>", "<date day=\"12\" month=\"12\" year=\"2018\"/>"));
        String[] input = {
                //1
                "<transaction>\n" +
                        "    <id>6753322</id>\n" +
                        "    <number region=\"Russia\">8-900-000-00-00</number>\n" +
                        "    <nonattr />\n" +
                        "    <nonattr></nonattr>\n" +
                        "    <nonattr>text</nonattr>\n" +
                        "    <attr id=\"1\" />\n" +
                        "    <attr id=\"2\"></attr>\n" +
                        "    <attr id=\"3\">text</attr>\n" +
                        "    <email>\n" +
                        "        <to>to_example@gmail.com</to>\n" +
                        "        <from>from_example@gmail.com</from>\n" +
                        "        <subject>Project discussion</subject>\n" +
                        "        <body font=\"Verdana\">Body message</body>\n" +
                        "        <date day=\"12\" month=\"12\" year=\"2018\"/>\n" +
                        "    </email>\n" +
                        "</transaction>"
                //2
                , "<id>6753322</id>\n" +
                "    <number region=\"Russia\">8-900-000-00-00</number>\n" +
                "    <nonattr />\n" +
                "    <nonattr></nonattr>\n" +
                "    <nonattr>text</nonattr>\n" +
                "    <attr id=\"1\" />\n" +
                "    <attr id=\"2\"></attr>\n" +
                "    <attr id=\"3\">text</attr>\n" +
                "    <email>\n" +
                "        <to>to_example@gmail.com</to>\n" +
                "        <from>from_example@gmail.com</from>\n" +
                "        <subject>Project discussion</subject>\n" +
                "        <body font=\"Verdana\">Body message</body>\n" +
                "        <date day=\"12\" month=\"12\" year=\"2018\"/>\n" +
                "    </email>"
                //3
                , "<to>to_example@gmail.com</to><from>from_example@gmail.com</from><subject>Project discussion</subject><body font=\"Verdana\">Body message</body><date day=\"12\" month=\"12\" year=\"2018\"/>"
        };
        for (int i = 0; i < input.length; i++) {
            List<String> actual = NestXML.findTagList(Utils.removeSpaceBetweenAngleBrackets(input[i]));
            assertEquals("actual=\n" + actual + "\nexpected=\n" + expected.get(i), expected.get(i).size(), actual.size());
        }
    }

    @Test
    public void testHasChildren() {
        String tag = "<nonattr />";
        String attr = findElementName(tag);
//        assertTrue(tag.matches(".*/>$"));
        assertFalse(NestXML.hasXmlChildren(tag));

        tag = "<id>6753322</id>";
        attr = findElementName(tag);
//        assertTrue(tag.matches(".*" + attr + ">$"));
        assertFalse(NestXML.hasXmlChildren(tag));

        tag = "<transaction>\n" +
                "    <id>6753322</id>\n" +
                "    <number region=\"Russia\">8-900-000-00-00</number>\n" +
                "    <nonattr />\n" +
                "    <nonattr></nonattr>\n" +
                "    <nonattr>text</nonattr>\n" +
                "    <attr id=\"1\" />\n" +
                "    <attr id=\"2\"></attr>\n" +
                "    <attr id=\"3\">text</attr>\n" +
                "    <email>\n" +
                "        <to>to_example@gmail.com</to>\n" +
                "        <from>from_example@gmail.com</from>\n" +
                "        <subject>Project discussion</subject>\n" +
                "        <body font=\"Verdana\">Body message</body>\n" +
                "        <date day=\"12\" month=\"12\" year=\"2018\"/>\n" +
                "    </email>\n" +
                "</transaction>";
        assertTrue(NestXML.hasXmlChildren(Utils.removeSpaceBetweenAngleBracketsAndLine(tag)));
    }

    @Test
    public void testPattern() {
        String input = inputArray[0];
        String firstKey = "transaction";
        final String regex = "(<" + firstKey + ".*>.*</" + firstKey + ">|<" + firstKey + ".*?/>)";
        System.out.println(regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String tag = matcher.group();
            System.out.println(tag);
            assertEquals(inputArray[0], tag);
        }
    }

    @Test
    public void test1() {
        for (int i = 0; i < inputArray.length; i++) {
            String input = inputArray[i];
            String actual = NestXML.nestingXml(input);
            String expect = expectArray[i];
            assertEquals(expect, actual);
            System.out.println("success " + i);
        }
    }

    String[] inputArray = new String[]{
            //1
            "<transaction>\n" +
                    "    <id>6753322</id>\n" +
                    "    <number region=\"Russia\">8-900-000-00-00</number>\n" +
                    "    <nonattr />\n" +
                    "    <nonattr></nonattr>\n" +
                    "    <nonattr>text</nonattr>\n" +
                    "    <attr id=\"1\" />\n" +
                    "    <attr id=\"2\"></attr>\n" +
                    "    <attr id=\"3\">text</attr>\n" +
                    "    <email>\n" +
                    "        <to>to_example@gmail.com</to>\n" +
                    "        <from>from_example@gmail.com</from>\n" +
                    "        <subject>Project discussion</subject>\n" +
                    "        <body font=\"Verdana\">Body message</body>\n" +
                    "        <date day=\"12\" month=\"12\" year=\"2018\"/>\n" +
                    "    </email>\n" +
                    "</transaction>",
            //2
            "<node>\n" +
                    "    <child name = \"child_name1\" type = \"child_type1\">\n" +
                    "        <subchild id = \"1\" auth=\"auth1\">Value1</subchild>\n" +
                    "    </child>\n" +
                    "    <child name = \"child_name2\" type = \"child_type2\">\n" +
                    "        <subchild id = \"2\" auth=\"auth1\">Value2</subchild>\n" +
                    "        <subchild id = \"3\" auth=\"auth2\">Value3</subchild>\n" +
                    "        <subchild id = \"4\" auth=\"auth3\"></subchild>\n" +
                    "        <subchild id = \"5\" auth=\"auth3\"/>\n" +
                    "    </child>\n" +
                    "</node>",
            //3
            "<node><child name=\"child_name1\" type=\"child_type1\"><subchild id=\"1\" auth=\"auth1\">Value1</subchild></child><child name=\"child_name2\" type=\"child_type2\"><subchild id=\"2\" auth=\"auth1\">Value2</subchild><subchild id=\"3\" auth=\"auth2\">Value3</subchild><subchild id=\"4\" auth=\"auth3\"></subchild><subchild id=\"5\" auth=\"auth3\"/></child></node>"
    };
    String[] expectArray = new String[]{
            //1
            "Element:\n" +
                    "path = transaction\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, id\n" +
                    "value = \"6753322\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, number\n" +
                    "value = \"8-900-000-00-00\"\n" +
                    "attributes:\n" +
                    "region = \"Russia\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, nonattr\n" +
                    "value = null\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, nonattr\n" +
                    "value = \"\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, nonattr\n" +
                    "value = \"text\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, attr\n" +
                    "value = null\n" +
                    "attributes:\n" +
                    "id = \"1\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, attr\n" +
                    "value = \"\"\n" +
                    "attributes:\n" +
                    "id = \"2\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, attr\n" +
                    "value = \"text\"\n" +
                    "attributes:\n" +
                    "id = \"3\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, email\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, email, to\n" +
                    "value = \"to_example@gmail.com\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, email, from\n" +
                    "value = \"from_example@gmail.com\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, email, subject\n" +
                    "value = \"Project discussion\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, email, body\n" +
                    "value = \"Body message\"\n" +
                    "attributes:\n" +
                    "font = \"Verdana\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = transaction, email, date\n" +
                    "value = null\n" +
                    "attributes:\n" +
                    "day = \"12\"\n" +
                    "month = \"12\"\n" +
                    "year = \"2018\"",
            //2
            "Element:\n" +
                    "path = node\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child\n" +
                    "attributes:\n" +
                    "name = \"child_name1\"\n" +
                    "type = \"child_type1\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = \"Value1\"\n" +
                    "attributes:\n" +
                    "id = \"1\"\n" +
                    "auth = \"auth1\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child\n" +
                    "attributes:\n" +
                    "name = \"child_name2\"\n" +
                    "type = \"child_type2\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = \"Value2\"\n" +
                    "attributes:\n" +
                    "id = \"2\"\n" +
                    "auth = \"auth1\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = \"Value3\"\n" +
                    "attributes:\n" +
                    "id = \"3\"\n" +
                    "auth = \"auth2\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = \"\"\n" +
                    "attributes:\n" +
                    "id = \"4\"\n" +
                    "auth = \"auth3\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = null\n" +
                    "attributes:\n" +
                    "id = \"5\"\n" +
                    "auth = \"auth3\"",
            //3
            "Element:\n" +
                    "path = node\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child\n" +
                    "attributes:\n" +
                    "name = \"child_name1\"\n" +
                    "type = \"child_type1\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = \"Value1\"\n" +
                    "attributes:\n" +
                    "id = \"1\"\n" +
                    "auth = \"auth1\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child\n" +
                    "attributes:\n" +
                    "name = \"child_name2\"\n" +
                    "type = \"child_type2\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = \"Value2\"\n" +
                    "attributes:\n" +
                    "id = \"2\"\n" +
                    "auth = \"auth1\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = \"Value3\"\n" +
                    "attributes:\n" +
                    "id = \"3\"\n" +
                    "auth = \"auth2\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = \"\"\n" +
                    "attributes:\n" +
                    "id = \"4\"\n" +
                    "auth = \"auth3\"\n" +
                    "\n" +
                    "Element:\n" +
                    "path = node, child, subchild\n" +
                    "value = null\n" +
                    "attributes:\n" +
                    "id = \"5\"\n" +
                    "auth = \"auth3\""
    };

}
