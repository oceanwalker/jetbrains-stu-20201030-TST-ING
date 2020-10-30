package test.cn.oceanwalker.java.FileProcessing.ReadingFiles;

import cn.oceanwalker.utils.Utils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestReadingFiles {
    @Test
    public void test() {
        String result = "";
        try {
            String path = "";
            path = getPathByRoot();
            path = getPathByRelative2();
            path = Utils.getPathByRelative(TestReadingFiles.class,"test.txt");
            result = Utils.readFileAsStringByPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("123", result);
    }
    @Test
    public void testDirect() {
        String result = "";
        try {
            final Class<TestReadingFiles> aClass = TestReadingFiles.class;
            final String fileName = "test.txt";
            result = Utils.getFileContent(aClass, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("123", result);
    }

    private String getPathByRelative2() {
        String path = TestReadingFiles.class.getResource("") + "test.txt";
        path = path.replace("file:/", "");
        path = path.replace("/", "//");
        System.out.println(path);
        return path;
    }

    private String getPathByRoot() {
        return "test.txt";
    }

}
