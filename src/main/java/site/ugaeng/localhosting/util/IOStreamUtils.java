package site.ugaeng.localhosting.util;

import java.io.*;

public class IOStreamUtils {

    public static BufferedReader getBufferedReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }

    public static BufferedWriter getBufferedWriter(OutputStream out) {
        return new BufferedWriter(new OutputStreamWriter(out));
    }
}
