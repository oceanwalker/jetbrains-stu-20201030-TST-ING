package test.cn.oceanwalker.basic_syntax_and_simple_programs.constructor;

import java.util.Scanner;

public class Main {

    public static String prepareFullName(String firstName, String lastName) {
        // write your code here
        if (firstName == null && lastName == null) {
            return "";
        } else if (firstName == null || lastName == null) {
            return firstName == null ? lastName : firstName;
        } else {
            return firstName + " " + lastName;
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        String firstName = scanner.nextLine();
        firstName = "null".equals(firstName) ? null : firstName;

        String lastName = scanner.nextLine();
        lastName = "null".equals(lastName) ? null : lastName;

        System.out.println(prepareFullName(firstName, lastName));
    }
}

class Box {

    double height;
    double width;
    double length;

    // write a method here
    public double getVolume() {
        return height * width * length;
    }
}

class Car {
    void accelerate() {
        speed += 5;
    }

    void brake() {
        speed -= 5;
        if (speed < 0) {
            speed = 0;
        }
    }

    int yearModel;
    String make;
    int speed;
}

class Clock {

    int hours = 12;
    int minutes = 0;

    void next() {
        // implement me
        if (minutes < 59) {
            minutes += 1;
        } else {
            minutes = 0;
        }
        if (minutes == 0) {
            if (hours < 12) {
                hours += 1;
            } else {
                hours = 1;
            }
        }
    }
}

class Account {
    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    private long balance;
    private String ownerName;
    private boolean locked;
}

class Complex {
    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    private double real;
    private double imaginary;
}

class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        if (firstName == null && lastName == null) {
            return "Unknown";
        } else if (firstName == null || lastName == null) {
            return firstName == null ? lastName : firstName;
        } else {
            return firstName + " " + lastName;
        }
    }
}

class Person {

    private static long nextId = 1;

    long id;
    String name;

    public Person(String name) {
        this.name = name; // (2)
        this.id = nextId;
        this.nextId++; // (3)
    }

    public long getNextId() { // (4)
        return nextId;
    }
}

class Main1 {

    public static void main(String[] args) {
        // write your code using the existing class ConstantsAndUtilities
        System.out.println(ConstantsAndUtilities.A_CONSTANT_TTT);
        System.out.println(ConstantsAndUtilities.B_CONSTANT_QQQ);
        System.out.println(ConstantsAndUtilities.getMagicString());
        System.out.println(ConstantsAndUtilities.convertStringToAnotherString("aa"));
    }

}

// Don't change this class
class ConstantsAndUtilities {

    public static final String A_CONSTANT_TTT = "1234";

    public static final String B_CONSTANT_QQQ = "7890";

    public static String getMagicString() {
        return A_CONSTANT_TTT + B_CONSTANT_QQQ;
    }

    public static String convertStringToAnotherString(String s) {
        return A_CONSTANT_TTT + s;
    }
}

class Cat {

    // write static and instance variables
    String name;
    int age;
    static int counter = 0;

    public Cat(String name, int age) {
        // implement the constructor
        // do not forget to check the number of cats
        this.name = name;
        this.age = age;
        counter++;
        if (counter > 5) {
            System.out.println("You have too many cats");
        }
    }

    public static int getNumberOfCats() {
        // implement the static method
        return counter;
    }
}

class Phone {
    public Phone(String ownerName, String countryCode, String cityCode, String number) {
        this.ownerName = ownerName;
        this.countryCode = countryCode;
        this.cityCode = cityCode;
        this.number = number;
    }

    public Phone(String ownerName, String number) {
        this.ownerName = ownerName;
        this.number = number;
        countryCode = "";
        cityCode = "";
    }

    String ownerName;
    String countryCode;
    String cityCode;
    String number;
}

class Time {
    public Time(int hours) {
        this.hours = hours;
    }

    public Time(int hours, int minutes) {
        seconds = 0;
        this.hours = hours;
        this.minutes = minutes;
    }

    public Time(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    int hours;
    int minutes;
    int seconds;
}

class Employee {

    String name;
    int salary;
    String address;

    public Employee() {
        name = "unknown";
        salary = 0;
        address = "unknown";
    }

    public Employee(String name, int salary) {
        address = "unknown";
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, int salary, String address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }
}