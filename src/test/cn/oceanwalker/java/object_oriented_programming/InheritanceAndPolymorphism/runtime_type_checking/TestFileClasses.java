package test.cn.oceanwalker.java.object_oriented_programming.InheritanceAndPolymorphism.runtime_type_checking;

import org.junit.Test;

class File { /* fields and methods */
}

class ImageFile extends File { /* fields and methods */
}

class TextFile extends File { /* fields and methods */
}

public class TestFileClasses {
    @Test
    public void test() {
        File file = new File();
        File img = new ImageFile();
        File txt = new TextFile();

        //false
        System.out.println(TextFile.class.isInstance(img));
        System.out.println(ImageFile.class.isInstance(file));

        //true
        System.out.println(TextFile.class.isInstance(new TextFile()));
        System.out.println(TextFile.class.isInstance(txt));
        System.out.println(ImageFile.class.isInstance(img));
        System.out.println(File.class.isInstance(img));
        System.out.println(File.class.isInstance(file));
    }
}
