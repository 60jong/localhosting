package site.ugaeng.localhosting.tunnel.impl.socket.io;

import site.ugaeng.localhosting.exception.LocalhostingException;
import site.ugaeng.localhosting.exception.SocketClosedException;
import site.ugaeng.localhosting.http.request.Request;

import java.io.*;
import java.net.Socket;

import static site.ugaeng.localhosting.util.ObjectUtils.*;
import static site.ugaeng.localhosting.util.StringUtils.hasText;

public class SocketDataLineReader implements Closeable {

    private final BufferedReader reader;

    public SocketDataLineReader(Socket socket) {
        try
        {
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            throw new LocalhostingException(e);
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
            throw new LocalhostingException(e);
        }
    }

    private static void ensureNonNull(String rawRequest) {
        if (!hasText(rawRequest)) {
            throw new SocketClosedException();
        }
    }

    public String readLine() {
        try
        {
            return reader.readLine();
        }
        catch (IOException e)
        {
            throw new LocalhostingException(e);
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
