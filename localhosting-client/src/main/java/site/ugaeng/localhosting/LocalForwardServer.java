package site.ugaeng.localhosting;

public class LocalForwardServer {

    private final ProxyConnector connector;

    public LocalForwardServer() {
        connector = new ProxyConnector();
    }

    public void run() {
        connector.start();
    }

}
