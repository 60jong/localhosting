package site.ugaeng.localhosting.http.local.request;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import static site.ugaeng.localhosting.util.ObjectUtils.convertToObject;

@Slf4j
public class RequestReader {

    public static Request readFromReader(BufferedReader reader) {

        final String json = readRequestJson(reader);

        return convertToObject(json, Request.class);
    }

    private static String readRequestJson(BufferedReader reader) {
        try {
            return Objects.requireNonNull(reader.readLine(), "Connection with TunnelingServer Closed");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
