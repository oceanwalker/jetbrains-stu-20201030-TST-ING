package cn.oceanwalker.jetbrains_academy.object_oriented_programming.inheritance_and_polymorphism.anonymous_classes;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        StringReverser reverser = new StringReverser() {
            @Override
            public String reverse(String str) {
                return new StringBuffer(str).reverse().toString();
            }
        };

        System.out.println(reverser.reverse(line));
    }

    interface StringReverser {

        String reverse(String str);
    }

}