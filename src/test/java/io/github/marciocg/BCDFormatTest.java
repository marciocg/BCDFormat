package io.github.marciocg;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import static io.github.marciocg.BCDFormat.fromBCDByteArrayToString;
import static io.github.marciocg.BCDFormat.fromStringToBCDByteArray;

/**
 * Unit test for BCDFormat static methods
 */
public class BCDFormatTest 
{
    /**
     * Test decoding from BCD data as byte[] array to String, and vice-versa
     */
    @Test
    public void fromBCDtoStringAndBackTest()
    {
        String input = "498455910123456789abcdefABCDEFa0eeccdd858fd9d0d1eaaecafebE";
        byte [] result = fromStringToBCDByteArray(input);
        String decoded = fromBCDByteArrayToString(result);

        assertEquals(input.toLowerCase(), decoded);
    }
}
