package ru.progwards.java1.lessons.bigints1;

import java.math.BigDecimal;
import java.math.BigInteger;

import static java.math.BigInteger.ONE;

public class BigAlgebra {

    static BigInteger fibonacci(int n) {
        BigInteger tmp;
        BigInteger fibo1 = BigInteger.ONE;
        BigInteger fibo2 = BigInteger.ONE;
        int i = 0;
        while (i++ < (n - 2)) {
            tmp = fibo1.add(fibo2);
            fibo1 = fibo2;
            fibo2 = tmp;
        }
        return fibo2;
    }

    static String intToBinary(int num) {
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            int tmp = num % 2;
            result.insert(0, tmp);
            num /= 2;
        }
        return result.toString();
    }

    static BigDecimal fastPow(BigDecimal num, int pow) {
        String powBinary = Integer.toBinaryString(pow);
        BigDecimal result = BigDecimal.ONE;
        for (char el : powBinary.toCharArray()) {
            if (Integer.parseInt(String.valueOf(el)) == 0) {
                result = result.multiply(result);
            } else {
                result = result.multiply(result).multiply(num);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int num = 150;
        BigDecimal bigDecimal = new BigDecimal("4");
        int pow = 5;
        //System.out.println(fibonacci(num));
        //System.out.println(Integer.toBinaryString(num));
        System.out.println(fastPow(bigDecimal, pow));
        System.out.println(bigDecimal.pow(pow));
    }
}
