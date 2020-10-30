package cn.oceanwalker.jetbrains_academy.object_oriented_programming.inheritance_and_polymorphism.anonymous_classes;

import java.util.Scanner;

public class ReturningValuesFromAnAnonymousClass {

    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);
        final String str = scanner.nextLine();
        final int number = Integer.parseInt(scanner.nextLine());

        Returner returner = new Returner() {
            @Override
            public String returnString() {
                return str;
            }

            @Override
            public int returnInt() {
                return number;
            }
        };

        System.out.println(returner.returnString());
        System.out.println(returner.returnInt());
    }
}

interface Returner {

    public String returnString();

    public int returnInt();
}
