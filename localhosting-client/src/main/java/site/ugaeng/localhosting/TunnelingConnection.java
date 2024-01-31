package site.ugaeng.localhosting;

import java.io.Closeable;

public interface TunnelingConnection extends Closeable {

    boolean isConnected();
}
