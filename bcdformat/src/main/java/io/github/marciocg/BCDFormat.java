package io.github.marciocg;

import java.io.FileOutputStream;
import java.io.IOException;

// https://stackoverflow.com/questions/45655484/bcd-to-decimal-and-decimal-to-bcd?rq=3
// https://stackoverflow.com/questions/28077671/algorithm-to-convert-a-string-of-decimal-digits-to-bcd
public final class BCDFormat {
    public static void main(String[] args) throws IOException {

        System.out.println(args[0] + " " + args[0].length() + " => Input");
        FileOutputStream fout = new FileOutputStream("saida.bin");

        var res = fromStringToBCDByteArray(args[0]);
        fout.write(res);
        fout.write((byte) 0x40);
        var str = fromBCDByteArrayToString(res);

        System.out.println(str + " " + str.length() + " => Output String e length() da String");
        fout.write(str.getBytes());
        // fout.write((byte) 0x40);
        fout.close();
    }

    public static byte[] fromStringToBCDByteArray(String arg) {
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

    public static String fromBCDByteArrayToString(byte[] arg) {
        int j = 0;
        char c;
        var sb = new StringBuilder();
        while (j < arg.length) {

            c = (char) ((arg[j] >>> 4) & 0x0f);
            if (c >= 0 && c <= 9) {
                c += 0x30;
            } else {
                c += 0x57;     // 0x37 for uppercase
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
