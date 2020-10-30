package cn.oceanwalker.jetbrains_academy.basic_syntax_and_simple_programs.methods.recursion;

public class NthPower {
    public static double pow(double a, long b) {
        if (1 == b) {
            return a;
        } else if (0 == b) {
            return 1;
        }
        if (0 == b % 2) {
            return pow(a * a, b / 2);
        } else {
            return a * pow(a, b - 1);
        }
    }
}
