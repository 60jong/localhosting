package site.ugaeng.localhostingserver.impl.socket.tunneling;

import site.ugaeng.localhostingserver.http.request.Request;

public class TunnelingProcessorFactory {

    public static TunnelProcessor createTunnelProcessorByRequest(Request request) {
        String requestUri = getRequestUri(request);

        if (requestUri.equals("/tunnels")) {
            return new TunnelRegisterer(request);
        }

        if (requestUri.startsWith("/connections")) {
            return new TunnelConnector(request);
        }

        throw new RuntimeException();
    }

    private static String getRequestUri(Request request) {
        return request.getRequestLine()
                      .getUri();
    }
}
