package cn.oceanwalker.jetbrains_academy.object_oriented_programming.inheritance_and_polymorphism.anonymous_classes;

public class ReturningFromAMethod {

    public static Runnable createRunnable(String text, int repeats) {
        return new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < repeats; i++) {
                    System.out.println(text);
                }
            }
        };
    }
}

