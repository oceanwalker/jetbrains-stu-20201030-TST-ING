package test.cn.oceanwalker.java.collections.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestArrayList {
    @Test
    public void test() {
        List<String> list = new ArrayList<>();

        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");

        assertFalse("third".equals(list.get(3)));
        assertFalse(list.contains("FIRST"));
        assertEquals(2, list.subList(0, 1).size());
        assertEquals(4, list.size());
    }
}
