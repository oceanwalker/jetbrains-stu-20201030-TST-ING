package test.cn.oceanwalker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestUrl {
    @Test
    public void test() {
        assertEquals("pass : 12345 " +
                "port : 8080 " +
                "cookie : not found " +
                "host : localhost " +
                "password : 12345 ", parseUrl("https://target.com/index.html?pass=12345&port=8080&cookie=&host=localhost"));
        assertEquals(
                "port : 8080 " +
                        "cookie : not found " +
                        "host : localhost "
                , parseUrl("https://target.com/index.html?pass=&port=8080&cookie=&host=localhost"));
        assertEquals(
                "port : 8080 " +
                        "name : Bob " +
                        "cookie : not found " +
                        "host : localhost "
                , parseUrl("https://target.com/index.html?port=8080&name=Bob&cookie=&host=localhost"));
        assertEquals("port : 8080 " +
                "cookie : not found " +
                "host : localhost ", parseUrl("https://target.com/index.html?port=8080&cookie=&host=localhost"));

    }

    private String parseUrl(String url) {
        StringBuilder result = new StringBuilder();
        String pass = new String();
        int start = url.indexOf('?');
        url = url.substring(start + 1);
        String[] pairs = url.split("&");
        for (String pair : pairs) {
            String[] split = pair.split("=");
            if (split.length == 1) {
                if(split[0].equals("pass")){
                    continue;
                }
                result.append(split[0]).append(" : ").append("not found ");
            } else {
                if (split[0].equals("pass")) {
                    pass = split[1];
                }
                result.append(split[0]).append(" : ").append(split[1]).append(" ");
            }
        }
        if (pass.length() != 0) {
            result.append("password : ").append(pass).append(" ");
        }
        return result.toString();
    }
}
