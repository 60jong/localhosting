package site.ugaeng.localhosting;

public class LocalForwardServer {

    private ProxyConnector connector;
    private boolean running;

    public void run() {

        while (running) {
            connector.connect();
        }
    }

}
