package test.cn.oceanwalker.java.object_oriented_programming.InheritanceAndPolymorphism.Interface;

import java.util.*;

public class CompactStringsWithAsciiCharSequence {

}

class AsciiCharSequence implements CharSequence {
    private final byte[] array;

    public AsciiCharSequence(byte[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public char charAt(int index) {
        return (char) array[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(array, start, end));
    }

    @Override
    public String toString() {
        return new String(array);
    }
// implementation
}