package site.ugaeng.localhostingserver.impl.socket.tunneling;

import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClient;
import site.ugaeng.localhostingserver.impl.socket.tunneling.client.TunnelClientRepository;

public class TunnelConnector implements TunnelRequestProcessor {

    private final TunnelClientRepository tunnelClientRepository;
    private final String tunnelName;

    public TunnelConnector(String tunnelName) {
        this.tunnelClientRepository = TunnelClientRepository.getInstance();
        this.tunnelName = tunnelName;
    }

    @Override
    public void run(TunnelClient client) {
        tunnelClientRepository.save(tunnelName, client);

        sendConnectedResponse(client);
    }

    private void sendConnectedResponse(TunnelClient client) {
        client.getClientWriter().writeWithLine("SUCCESS");
    }
}
