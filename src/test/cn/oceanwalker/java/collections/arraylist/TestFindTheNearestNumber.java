package test.cn.oceanwalker.java.collections.arraylist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestFindTheNearestNumber {
    @Test
    public void test() {
        assertEquals("2 4", find("1 2 4 5", "3"));
        assertEquals("4", find("1 2 3 4", "6"));
        assertEquals("3 3 5 5", find("5 1 3 3 1 5", "4"));
    }

    private String find(String raw, String target) {
        int dis = Integer.MAX_VALUE;
        int t = Integer.parseInt(target);
        for (String s : raw.split(" ")) {
            final int curDis = Math.abs(Integer.parseInt(s) - t);
            if (curDis < dis) {
                dis = curDis;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (String s : raw.split(" ")) {
            final int i = Integer.parseInt(s);
            final int curDis = Math.abs(i - t);
            if (curDis == dis) {
                list.add(i);
            }
        }
        Collections.sort(list);
        String result = "";
        for (Integer integer : list) {
            result += integer + " ";
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }
}

