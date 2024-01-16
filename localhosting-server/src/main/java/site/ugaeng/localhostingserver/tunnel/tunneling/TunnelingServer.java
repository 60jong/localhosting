package site.ugaeng.localhostingserver.forward;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TunnelingServer {

    private final TunnelingProcessor processor;

    public TunnelingServer() {
        processor = new TunnelingProcessor();
    }

    public void run() {
        log.info("Tunneling started");
        processor.start();
    }
}
