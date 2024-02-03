package site.ugaeng.localhostingserver.impl.socket.io;

import lombok.Getter;
import org.springframework.util.StringUtils;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.utils.ObjectUtils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import static org.springframework.util.StringUtils.*;
import static site.ugaeng.localhostingserver.utils.ObjectUtils.*;

public class SocketDataLineReader implements Closeable {

    private final BufferedReader reader;

    public SocketDataLineReader(Socket socket) {
        try
        {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public Request readRequestByLine() {
        try
        {
            String rawRequest = reader.readLine();

            ensureNonNull(rawRequest);

            return convertToObject(rawRequest, Request.class);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void ensureNonNull(String rawRequest) {
        if (!hasText(rawRequest)) {
            throw new RuntimeException();
        }
    }

    public String readLine() {
        try
        {
            return reader.readLine();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }

    public BufferedReader getBufferedReader() {
        return reader;
    }
}