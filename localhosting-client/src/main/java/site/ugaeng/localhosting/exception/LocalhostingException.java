package site.ugaeng.localhosting.exception;

public  class LocalhostingException extends RuntimeException {

    public LocalhostingException() {

    }

    public LocalhostingException(String message) {
        super(message);
    }

    public LocalhostingException(String message, Exception e) {
        super(message, e);
    }

    public LocalhostingException(Exception e) {
        super(e);
    }
}
