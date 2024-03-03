/* 
Copyright 2024 Márcio Conceição Goulart

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. 
*/
package io.github.marciocg;

/**
 * A class that contains static methods to decode/encode BCD data as String and
 * byte[] array.
 * 
 * @author marciocg
 * @version v0.1.0
 * @since 01/03/2024
 */
public final class BCDFormat {

    /**
     * Takes a {@link String} with hexadecimal characters and formats (encodes) as a byte[] array.
     * 
     * @param arg Data as hexadecimal characters
     * @return byte[] array of BCD encoded data
     * @throws IllegalArgumentException for illegal hexadecimal characters on {@link String} argument passed
     */
    public static byte[] fromStringToBCDByteArray(String arg) throws IllegalArgumentException {
        int i = 0;
        int p = 0;
        byte bcd = 0;
        byte[] arr = arg.getBytes();
        byte[] res = new byte[arr.length / 2];

        while (i < arr.length) {

            if (arr[i] >= 48 && arr[i] <= 57) {
                bcd = (byte) (((arr[i] - 0x30) * 0x10)); // 0-9
            } else if (arr[i] >= 65 && arr[i] <= 70) { // A-F
                bcd = (byte) ((((arr[i] - 0x40) & 0x0f) + 0x09) * 0x10);
            } else if (arr[i] >= 97 && arr[i] <= 102) { // a-f
                bcd = (byte) ((((arr[i] - 0x60) & 0x0f) + 0x09) * 0x10);
            } else {
                throw new IllegalArgumentException("Invalid char at offset " + i);
            }

            i++;

            if (arr[i] >= 48 && arr[i] <= 57) {
                bcd += (arr[i] - 0x30);
            } else if (arr[i] >= 65 && arr[i] <= 70) { // A-F
                bcd += (byte) (((arr[i] - 0x40) & 0x0f) + 0x09);
            } else if (arr[i] >= 97 && arr[i] <= 102) { // a-f
                bcd += (byte) (((arr[i] - 0x60) & 0x0f) + 0x09);
            } else {
                throw new IllegalArgumentException("Invalid char at offset " + i);
            }

            res[p] = bcd;

            i++;

            p++;
        }
        return res;
    }

    /**
     * Takes a byte[] array with BCD encoded data and parses (decodes) into {@link String} with hexadecimal characters as US_ASCII.
     * 
     * @param arg a byte[] array of BCD encoded data
     * @return {@link String} with hexadecimal characters as US_ASCII
     */
    public static String fromBCDByteArrayToString(byte[] arg) {
        int j = 0;
        char c;
        var sb = new StringBuilder();
        while (j < arg.length) {

            c = (char) ((arg[j] >>> 4) & 0x0f);
            if (c >= 0 && c <= 9) {
                c += 0x30;
            } else {
                c += 0x57; // 0x37 for uppercase
            }
            sb.append(c);

            c = (char) (arg[j] & 0x0f);
            if (c >= 0 && c <= 9) {
                c += 0x30;
            } else {
                c += 0x57;
            }
            sb.append(c);
            j++;
        }

        return sb.toString();
    }
}
