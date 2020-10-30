package test.cn.oceanwalker.json_xml_converter.stage1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class stage_1_6_elementary {
    @Test
    public void test() {
        assertEquals("{\"host\":\"127.0.0.1\"}", convert("<host>127.0.0.1</host>"));
        assertEquals("<jdk>1.8.9</jdk>", convert("{\"jdk\" : \"1.8.9\"}"));
        assertEquals("{\"success\": null }", convert("<success/>"));
        assertEquals("<storage/>", convert("{ \"storage\" : null }"));
    }

    private String convert(String input) {
        StringBuilder result = new StringBuilder();
        if (input.startsWith("<")) {
            String key = "";
            String value = "";
            if (input.indexOf("/>") > -1) {
                key = "\"" + input.substring(input.indexOf("<") + 1, input.indexOf("/")) + "\"";
                value = " null ";
            } else {
                key = "\"" + input.substring(input.indexOf("<") + 1, input.indexOf(">")) + "\"";
                input = input.substring(input.indexOf(">"), input.length());
                value = "\"" + input.substring(input.indexOf(">") + 1, input.indexOf("<")) + "\"";

            }
            result.append("{").append(key).append(":").append(value).append("}");
        } else {
            final String[] split = input.split("\"");
            if (input.indexOf("null") > -1) {
                String key = split[1];
                result.append("<").append(key).append("/>");
            } else {
                String key = split[1];
                String value = split[3];
                result.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
            }
        }
        System.out.println(result);
        return result.toString();
    }
}
