package test.cn.oceanwalker.json_xml_converter.stage6;

import cn.oceanwalker.jetbrains_academy.json_xml_converter.stage6.AdvancedConverter;
import cn.oceanwalker.utils.Utils;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * @author Eagle
 */
public class AdvancedConverterTest {
    @Test
    public void testAll() {
        for (int i = 1; i <= 6; i++) {
            testCase(i);
        }
    }

    private void testCase(int i) {
        try {
            assertEquals(Utils.removeSpaceAndLine(Utils.getFileContent(AdvancedConverterTest.class, "output(" + i + ").txt")), Utils.removeSpaceAndLine(AdvancedConverter.convert(Utils.getFileContent(AdvancedConverterTest.class, "input(" + i + ").txt"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(i + " / 6 通过!");
    }
}
