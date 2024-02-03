package site.ugaeng.localhostingserver.impl.socket.tunneling;

import site.ugaeng.localhostingserver.impl.socket.tunneling.request.TunnelRequest;

public class TunnelingProcessorFactory {

    public static TunnelRequestProcessor createTunnelRequestProcessor(TunnelRequest request) {
        String requestType = request.getType();

        if (requestType.equals("CONNECTION")) {
            return new TunnelConnector(request.getTunnelName());
        }

        throw new RuntimeException();
    }
}
