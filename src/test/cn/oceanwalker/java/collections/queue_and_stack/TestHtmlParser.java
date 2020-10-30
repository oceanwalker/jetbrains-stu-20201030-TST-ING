package test.cn.oceanwalker.java.collections.queue_and_stack;

import cn.oceanwalker.jetbrains_academy.java.Collections.queue_and_stack.HtmlParser;
import cn.oceanwalker.jetbrains_academy.java.Collections.queue_and_stack.HtmlParser2;
import cn.oceanwalker.jetbrains_academy.java.Collections.queue_and_stack.HtmlParser3;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestHtmlParser {
    String[] input = {
            //1
            "<html>content</html>"
            //2
            , "<html>\n" +
            "  <h1>content1</h1>\n" +
            "  <h2>content2</h2>\n" +
            "</html>"
            //3
            , "<html>\n" +
            "  <h1>content1</h1>\n" +
            "  <div>\n" +
            "    <h2>content2</h2>\n" +
            "  </div>\n" +
            "</html>"
            //4
            , "<html><a>hello</a><h1><h4>nestedHello</h4><h3>nestedWorld</h3><h6><br>top</br></h6></h1><br>world</br></html>\n"
            //5
            , "<html> <a>hello</a> <h1> <h1>nestedHello</h1> <h3>nestedWorld</h3> <h6> <br>top</br> </h6> </h1> <br>world</br> </html>"
    };
    String[] expected = {
            //1
            "content"
            //2
            , "content1\n" +
            "content2\n" +
            "<h1>content1</h1><h2>content2</h2>"
            //3
            , "content1\n" +
            "content2\n" +
            "<h2>content2</h2>\n" +
            "<h1>content1</h1><div><h2>content2</h2></div>"
            //4
            , "hello\n" +
            "nestedHello\n" +
            "nestedWorld\n" +
            "top\n" +
            "<br>top</br>\n" +
            "<h4>nestedHello</h4><h3>nestedWorld</h3><h6><br>top</br></h6>\n" +
            "world\n" +
            "<a>hello</a><h1><h4>nestedHello</h4><h3>nestedWorld</h3><h6><br>top</br></h6></h1><br>world</br>"
            //5
            , "hello\n" +
            "nestedHello\n" +
            "nestedWorld\n" +
            "top\n" +
            "<br>top</br>\n" +
            "<h1>nestedHello</h1><h3>nestedWorld</h3><h6><br>top</br></h6>\n" +
            "world\n" +
            "<a>hello</a><h1><h1>nestedHello</h1><h3>nestedWorld</h3><h6><br>top</br></h6></h1><br>world</br>"
    };

    @Test
    public void test() {
        for (int i = 0; i < expected.length; i++) {
            assertEquals("expected=" + expected[i] + "\nactual=" + HtmlParser.parse(input[i]), expected[i], HtmlParser.parse(input[i]));
            System.out.println((i + 1) + " / " + expected.length);
        }
    }

    @Test
    public void test2() {
        for (int i = 0; i < expected.length; i++) {
            assertEquals("expected=" + expected[i] + "\nactual=" + HtmlParser2.parse(input[i]), expected[i], HtmlParser2.parse(input[i]));
            System.out.println((i + 1) + " / " + expected.length);
        }
    }

    @Test
    public void test3() {
        for (int i = 0; i < expected.length; i++) {
            assertEquals("expected=" + expected[i] + "\nactual=" + HtmlParser3.parse(input[i]), expected[i], HtmlParser3.parse(input[i]));
            System.out.println((i + 1) + " / " + expected.length);
        }
    }

    @Test
    public void testGetList() {
        List<String> expect = List.of("<a>hello</a>");
        assertEquals(expect, HtmlParser.getList("<a>hello</a>"));
        expect = List.of("<a>hello</a>", "<h1><h4>nestedHello</h4><h3>nestedWorld</h3><h6><br>top</br></h6></h1>", "<br>world</br>");
        assertEquals(expect, HtmlParser.getList("<a>hello</a><h1><h4>nestedHello</h4><h3>nestedWorld</h3><h6><br>top</br></h6></h1><br>world</br>"));
        expect = List.of("<a>hello</a>", "<h1><h1>nestedHello</h1><h3>nestedWorld</h3><h6><br>top</br></h6></h1>", "<br>world</br>");
        assertEquals(expect, HtmlParser.getList("<a>hello</a><h1><h1>nestedHello</h1><h3>nestedWorld</h3><h6><br>top</br></h6></h1><br>world</br>"));
    }

    @Test
    public void testGetContent() {
        assertEquals("top", HtmlParser.getContent("<br>top</br>"));
        assertEquals("<br>top</br>", HtmlParser.getContent("<h6><br>top</br></h6>"));
    }

    @Test
    public void testHasChild() {
        assertFalse(HtmlParser.hasChild("<br>top</br>"));
        assertFalse(HtmlParser.hasChild("<br></br>"));
        assertFalse(HtmlParser.hasChild("<br > </br> "));
        assertTrue(HtmlParser.hasChild("<h6><br>top</br></h6>"));
    }
}
