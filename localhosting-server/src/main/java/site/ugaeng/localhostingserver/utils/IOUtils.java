package site.ugaeng.localhostingserver.utils;

import java.io.*;

public class IOUtils {

    public static BufferedReader getReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }

    public static BufferedWriter getWriter(OutputStream out) {
        return new BufferedWriter(new OutputStreamWriter(out));
    }
}
