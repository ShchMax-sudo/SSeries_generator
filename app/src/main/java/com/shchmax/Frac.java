package com.shchmax;

import java.math.BigInteger;
import java.lang.StringBuilder;

public class Frac {
    private BigInteger num = BigInteger.ZERO; // numerator
    private BigInteger den = BigInteger.ONE; // denominator

    public Frac() {}

    public Frac(BigInteger num, BigInteger den) {
        BigInteger gcd = num.gcd(den);
        this.num = num.divide(gcd);
        this.den = den.divide(gcd);
        if (den.signum() < 0) {
            this.num = this.num.negate();
            this.den = this.den.negate();
        }
    }

    public Frac(int num, int den) {
        this(new BigInteger(Integer.toString(num)), new BigInteger(Integer.toString(den)));
    }

    public void print() {
        String numerator = num.toString();
        String denominator = den.toString();
        int sz = Math.max(numerator.length(), denominator.length());
        for (int i = 0; i < sz - numerator.length(); ++i) {
            System.out.print(" ");
        }
        System.out.println(numerator);
        for (int i = 0; i < sz; ++i) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < sz - denominator.length(); ++i) {
            System.out.print(" ");
        }
        System.out.println(denominator);
        System.out.println();
    }

    public void print(StringBuilder numerator, StringBuilder minus, StringBuilder denominator) {
        String numm = num.toString();
        String denn = den.toString();
        int sz = Math.max(numm.length(), denn.length());
        for (int i = 0; i < sz - numm.length(); ++i) {
            numerator.append(" ");
        }
        numerator.append(numm);
        for (int i = 0; i < sz; ++i) {
            minus.append("-");
        }
        for (int i = 0; i < sz - denn.length(); ++i) {
            denominator.append(" ");
        }
        denominator.append(denn);
    }

    public Frac negate() {
        return new Frac(this.num.negate(), this.den);
    }

    public Frac reverse() {
        return new Frac(this.den, this.num);
    }

    public Frac add(Frac b) {
        BigInteger lcm = this.den.multiply(b.den).divide(this.den.gcd(b.den));
        return new Frac(this.num.multiply(lcm.divide(this.den)).add(b.num.multiply(lcm.divide(b.den))), lcm);
    }

    public Frac substract(Frac b) {
        return this.add(b.negate());
    }

    public Frac multiply(Frac b) {
        return new Frac(this.num.multiply(b.num), this.den.multiply(b.den));
    }

    public Frac divide(Frac b) {
        return this.multiply(b.reverse());
    }
}
