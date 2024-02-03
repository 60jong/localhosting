package site.ugaeng.localhosting.tunnel.impl.socket.io;

import site.ugaeng.localhosting.exception.LocalhostingException;
import site.ugaeng.localhosting.http.response.Response;

import java.io.*;
import java.net.Socket;

import static site.ugaeng.localhosting.util.ObjectUtils.convertToString;

public class SocketDataLineWriter implements Closeable {

    private final BufferedWriter writer;

    public SocketDataLineWriter(Socket socket) {
        try
        {
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e)
        {
            throw new LocalhostingException(e);
        }
    }

    public void writeResponseWithLine(Response response) {
        try
        {
            writer.write(convertToString(response));
            writer.newLine();
            writer.flush();
        }
        catch (IOException e)
        {
            throw new LocalhostingException(e);
        }
    }

    public void writeWithLine(String data) {
        try
        {
            writer.write(data);
            writer.newLine();
            writer.flush();
        }
        catch (IOException e)
        {
            throw new LocalhostingException(e);
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
