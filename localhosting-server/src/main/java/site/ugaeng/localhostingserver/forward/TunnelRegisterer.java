package site.ugaeng.localhostingserver.forward;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhostingserver.tunnel.TunnelClient;
import site.ugaeng.localhostingserver.utils.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

import static site.ugaeng.localhostingserver.utils.IOUtils.getReader;
import static site.ugaeng.localhostingserver.utils.IOUtils.getWriter;

@Slf4j
public class TunnelRegisterer implements Runnable {

    private final Socket registerClient;
    private final BufferedReader clientReader;
    private final BufferedWriter clientWriter;

    private final TunnelClientRepository tunnelClientRepository;

    public TunnelRegisterer(Socket registerClient) throws IOException {
        this.registerClient = registerClient;
        this.clientReader = getReader(registerClient.getInputStream());
        this.clientWriter = getWriter(registerClient.getOutputStream());

        this.tunnelClientRepository = TunnelClientRepository.getInstance();
    }

    @Override
    public void run() {
        try {
            final String requestTunnelName = clientReader.readLine();

            if (!isExistingTunnelName(requestTunnelName)) {
                log.info("new Tunnel, Tunnel Name : {}", requestTunnelName);
                registerTunnel(requestTunnelName);
                return;
            }
            log.info("exising Tunnel, Tunnel Name : {}", requestTunnelName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isExistingTunnelName(String requestTunnelName) {
        return tunnelClientRepository.find(requestTunnelName)
                                     .isPresent();
    }

    private void registerTunnel(String tunnelName) throws IOException {
        tunnelClientRepository.save(tunnelName, new TunnelClient(registerClient, clientReader, clientWriter));
    }
}
