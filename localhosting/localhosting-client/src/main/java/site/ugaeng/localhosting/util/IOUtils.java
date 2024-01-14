package site.ugaeng.localhosting.util;

import java.io.BufferedReader;
import java.io.IOException;

public class IOUtils {

    public static String readNBytes(BufferedReader reader, int byteLength) throws IOException {
        char bodyChars[] = new char[byteLength];
        reader.read(bodyChars, 0, byteLength);

        return String.copyValueOf(bodyChars)
                     .trim(); // except NULL char
    }
}
