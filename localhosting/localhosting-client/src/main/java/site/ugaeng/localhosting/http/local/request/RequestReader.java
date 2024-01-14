package site.ugaeng.localhosting.http.local.request;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class RequestReader {

    private static final ObjectMapper om = new ObjectMapper();

    public static Request readFromReader(BufferedReader reader) throws IOException {
        final String json = readJson(reader);

        return om.readValue(json, Request.class);
    }

    private static String readJson(BufferedReader reader) {
        return reader.lines()
                     .collect(Collectors.joining());
    }
}
