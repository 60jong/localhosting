package site.ugaeng.localhostingserver.impl.socket.io;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import static site.ugaeng.localhostingserver.utils.ObjectUtils.*;

public class SocketDataLineWriter implements Closeable {

    private final BufferedWriter writer;

    public SocketDataLineWriter(Socket socket) {
        try
        {
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void writeObjectWithLine(Object obj) {
        try
        {
            writer.write(convertToString(obj));
            writer.newLine();
            writer.flush();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }

    public BufferedWriter getBufferedWriter() {
        return writer;
    }
}
