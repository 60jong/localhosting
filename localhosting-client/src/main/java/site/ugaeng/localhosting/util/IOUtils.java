package site.ugaeng.localhosting.util;

import java.io.*;

public class IOUtils {

    public static String readNBytes(BufferedReader reader, int byteLength) throws IOException {
        char bodyChars[] = new char[byteLength];
        reader.read(bodyChars, 0, byteLength);

        return String.copyValueOf(bodyChars)
                     .trim(); // except NULL char
    }

    public static BufferedReader getReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }

    public static BufferedWriter getWriter(OutputStream out) {
        return new BufferedWriter(new OutputStreamWriter(out));
    }
}
