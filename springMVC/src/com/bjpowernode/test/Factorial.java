package com.bjpowernode.test;

import org.junit.Test;

public class Factorial {
    @Test
    public void test() {
        System.out.println(factorial(5));
        System.out.println(factorial2(5,1));
    }

    public int factorial(int n) {
        if (n < 0) {
            return 0;
        } else if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public int factorial2(int n, int res) {
        if (n < 0) {
            return 0;
        } else if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return res;
        } else {
            return factorial2(n - 1, n * res);
        }

    }
}
