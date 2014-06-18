package com.wilke.operators;

/**
 * JVM parameter: -ea
 */
public class BitchIfting {
   
    public static void playWithLiterals() {
        byte d = 7;
        byte h = 0x007;
        byte b = 0b111;
       
        assert d == h;
        assert d == b;
       
        System.out.println(Integer.toBinaryString(d));
        System.out.println(Integer.toBinaryString(h));
        System.out.println(Integer.toBinaryString(b));
    }
 
    public static void playWithBitwiseOperators() {
        byte a = 0b0001_1010; // 2 + 8 + 16 = 26
        byte b = 0b0101_1000; // 8 + 16 + 64 = 88
 
        System.out.println(Integer.toBinaryString(a | b)); // 0101_1010 = 2 + 8 + 16 + 64 = 90
        System.out.println(Integer.toBinaryString(a & b)); // 0001_1000 = 8 + 16 = 24
        System.out.println(Integer.toBinaryString(a ^ b)); // 0100_0010 = 2 + 64 = 66
       
        /**
         * unary complement operator (inversion of bits)
         * two's complement: 1 ^ number + 1
         */
        int c = 0b1111_1111_1111_1111_1111_1111_1111_1110;
        System.out.println(Integer.toBinaryString(~c)); // 1;
    }
   
    public static void playWithBitshiftOperators() {
        int a = 0b1000_0000_0000_0000_0000_0000_0011_0000;
 
        System.out.println(Integer.toBinaryString(a <<  2)); // 0000_0000_0000_0000_0000_0000_1100_0000, 2x 0-bits appended (on the right)
        // ↑ and ↓ are contrary
        System.out.println(Integer.toBinaryString(a >>> 2)); // 1000_0000_0000_0000_0000_0000_0000_1100, sign bit untouched, 2x 0-bits inserted afterwards
       
        System.out.println(Integer.toBinaryString(a >>  2)); // 1110_0000_0000_0000_0000_0000_0000_1100, 2x sign bits appended (on the left)
    }

    public static void main(final String[] args) {
       
        System.out.println("playWithLiterals");
        playWithLiterals();
       
        System.out.println();
        System.out.println("playWithBitwiseOperators");
        playWithBitwiseOperators();
       
        System.out.println();
        System.out.println("playWithBitshiftOperators");
        playWithBitshiftOperators();
 
    }
}
