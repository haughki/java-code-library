package org.haughki.codeLibrary;

/*
Complicated.  In Java, a byte is an 8-bit signed two's complement integer which
can represent vals from -128 to 127.  Unfortunately, there's no java lib method to print
the actual bit-string of a byte. Java provides Integer.toBinaryString() and
Integer.parseInt(<binary string>), but those work on 32-bit ints.  This is confusing,
because while a Java byte can't go higher than 127, an int can.  So, to explore 8-bit values
over 127, you need to work with ints, and then limit the print output to 8 bits.  Some
of the code below does that.

But, it's also interesting to see how Java represents its byte values; especially, how
it deals with negative values using two's complement.  Unfortunately, while you can see
the output of this, you can't (easily) create a byte from a negative two's complement
string:  Byte.parseByte() expects 7-bit bytes which you can sign negative ("-") to
create the negative version of a given positive byte value; i.e.,
Byte.parseByte("-0000001") is -1, whereas the actual two's complement version would
be 1111 1111.
 */
public class Bytes {
    public static void main(String[] args) {

        System.out.println("Useful bitmasks:");
        int[] a = {1, 2, 4, 8, 16, 32, 64, 128, 127, 191, 223, 239, 247, 251, 253, 254};
        for (int i : a) {
            print8Bits(i);
        }

        System.out.println();
        System.out.println("Bitwise operators and masking:");
        int x = Integer.parseInt("10010110", 2);
        print8Bits(x);
        print8Bits(x | Integer.parseInt("11000000", 2), "OR  with 1 - | - turn a bit(s) on");
        print8Bits(x & Integer.parseInt("11100111", 2), "AND with 0 - & - turn a bit(s) off");
        print8Bits(x & Integer.parseInt("00110000", 2), "AND with 1 - & - check bit(s) status");
        print8Bits(x ^ Integer.parseInt("00000011", 2), "XOR with 1 - ^ - (exclusive OR) toggle bit(s)");
        print8Bits(~((byte) x), "NOT - ~ - flip all bits");  // must cast to byte, else flips all 32 int bits

        /* Confusing.  The >> operator will replace the new, left bits with 1s if the leftmost significant
         bit of the starting number is 1, otherwise it replaces with 0s.  This is an "arithmetic" right shift
         aka a signed right shift. This corresponds to the fact that the leftmost bit of two's complement,
         negative, signed integers will always be 1.

         Using 8-bit values, it's impossible(?) to get Java to show the behavior of the shift operators.  This
         is because Java converts a byte to an int when applying a shift operator.  Show, to show how these
         operators work, I'm using 32 bits.
         */
        System.out.println();
        System.out.println("Bit shift operators:");
        x = Integer.parseInt("01001001", 2);
        print32Bits(x, "");
        print32Bits(x << 1, "<<  - signed left shift");
        print32Bits(x >> 1, ">>  - signed right shift");
        print32Bits(-50, "");
        print32Bits(-50 >> 1, ">>  - signed (arithmetic) right shift with negative number");
        print32Bits(-50 >>> 1, ">>  - unsigned (logical) right shift with negative number");

        System.out.println();
        System.out.println("Integer boundaries:");
        print32Bits(2147483647, "");
        print32Bits(-2147483648, "");

        System.out.println();
        System.out.println("Negative vs. positive numbers:");


        for (byte n = 1; n < 10; n++) {
            printByte(n, "");
        }

        for (byte n = -1; n > -10; n--) {
            printByte(n, "");
        }
    }

    private static void print8Bits(int i) {
        print8Bits(i, "");
    }

    private static void print8Bits(int i, String message) {
        String dec = String.format("%4s", Integer.toString(i));
        String hex = String.format("0x%2s", Integer.toHexString(i)).replace(' ', '0');
        String bin = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');

        // negative numbers will have 3 "extra" bytes of 1 because of the 32 bit int.  We just need the 8 bits
        // so strip off the meaningless lefthand 1 bits if they exist.
        if (bin.length() > 8) {
            bin = bin.substring(bin.length() - 8);
            hex = "0x" + hex.substring(hex.length() - 2);
        }
        System.out.println(String.format("%s %s %s", dec, hex, bin) + (message == "" ? "" : " -- " + message));
    }

    private static void print32Bits(int i, String message) {
        String dec = String.format("%11s", Integer.toString(i));
        String hex = String.format("0x%8s", Integer.toHexString(i)).replace(' ', '0');
        String bin = String.format("%32s", Integer.toBinaryString(i)).replace(' ', '0');

        String newBin = "";
        for (int j = 1; j < 33; j++) {
            newBin += bin.charAt(j - 1);
            if (j % 8 == 0 && j != 32) {
                newBin += " ";
            }
        }

        System.out.println(String.format("%s %s %s", dec, hex, newBin) + (message == "" ? "" : " -- " + message));
    }

    private static void printByte(byte b, String message) {
        //reversed:  {"00000001", "00000010", "00000100", "00001000", "00010000", "00100000", "01000000", "10000000"};
        byte[] masks = {-128, 64, 32, 16, 8, 4, 2, 1};
        String bitString = "";
        for (byte mask : masks) {
            if ((b & mask) == mask)
                bitString += "1";
            else
                bitString += "0";
        }
        System.out.println(String.format("%4s, %8s", b, bitString) + (message == "" ? "" : " -- " + message));
    }
}
