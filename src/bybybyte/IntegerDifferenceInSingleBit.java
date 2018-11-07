package bybybyte;

/**
 * given two integers, check are they differ by only single bit or not
 * <p>
 * exmaple:
 * (0,1)-> true
 * (1,2)-> false
 * 00
 * 01
 * <p>
 * <p>
 * 000 0001 010 011  100 101 110 111
 * <p>
 * 110
 * 111
 * <p>
 * 10010
 * 10001
 */

public class IntegerDifferenceInSingleBit {
    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        IntegerDifferenceInSingleBit test = new IntegerDifferenceInSingleBit();
        System.out.println(test.isGray(a, b));
    }

    private boolean isGray(int a, int b) {
        int result = a ^ b;
        while (result > 0) {
            if (result % 2 == 1 && result >> 1 > 0) return false;
            result = result >> 1;
        }
    }
}
