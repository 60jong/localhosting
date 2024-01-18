package site.ugaeng.localhosting.exception;

public class SocketClosedException extends LocalhostingException {

    public SocketClosedException() {
    }

    public SocketClosedException(Exception e) {
        super(e);
    }
}
