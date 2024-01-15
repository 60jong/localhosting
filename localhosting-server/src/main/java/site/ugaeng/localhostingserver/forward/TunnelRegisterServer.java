package site.ugaeng.localhostingserver.forward;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TunnelRegisterServer {

    private final TunnelRegisterProcessor processor;

    public TunnelRegisterServer() {
        processor = new TunnelRegisterProcessor();
    }

    public void run() {
        log.info("TunnelRegisterServer started");
        processor.start();
    }
}
