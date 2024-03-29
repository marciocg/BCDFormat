package io.github.marciocg;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import static io.github.marciocg.BCDFormat.fromBCDByteArrayToString;
import static io.github.marciocg.BCDFormat.fromStringToBCDByteArray;

/**
 * Unit test for BCDFormat static methods
 */
public class BCDFormatTest {

    /**
     * Test decoding from BCD data as byte[] array to String, and vice-versa
     */
    @Test
    public void fromBCDtoStringAndBackTest() {
        String input = "498455910123456789";
        // String input = "498455910123456789abcdefABCDEFa0eeccdd858fd9d0d1eaaecafebE";
        byte[] result = fromStringToBCDByteArray(input);
        String decoded = fromBCDByteArrayToString(result);

        assertEquals(input.toLowerCase(), decoded);
    }

    /**
     * Test with invalid hexadecimal character on second position of a pair from
     * String input, expects IllegalArgumentException
     */
    // @Test(expected = IllegalArgumentException.class)
    // public void fromStringWithInvalidHexChar2() {
    //     String input = "498455910123456789abcdefABCDEFa0eeccdd858fd9d0d1eaaecafebW";
    //     byte[] result = fromStringToBCDByteArray(input);
    //     String decoded = fromBCDByteArrayToString(result);
    // }

    /**
     * Test with invalid hexadecimal character on first position of a pair from
     * String input, expects IllegalArgumentException
     */
    // @Test(expected = IllegalArgumentException.class)
    // public void fromStringWithInvalidHexChar1() {
    //     String input = "498455910123456789abcdefABCDEFa0eeccdd858fd9d0d1eaaecafeWb";
    //     byte[] result = fromStringToBCDByteArray(input);
    //     String decoded = fromBCDByteArrayToString(result);
    // }

    /**
     * Test with odd amount of digits, expects ArrayIndexOutOfBoundsException
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void fromStringWithOddDigitsLength() {
        String input = "49845";
        byte[] result = fromStringToBCDByteArray(input);
        String decoded = fromBCDByteArrayToString(result);
    }

}
