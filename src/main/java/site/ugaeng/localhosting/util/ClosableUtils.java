package site.ugaeng.localhosting.util;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;
import java.io.IOException;

@Slf4j
public class ClosableUtils {

    public static void close(Closeable closeable) {
        try {
            closeable.close();
            log.info("Closable [{}] closed", closeable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
