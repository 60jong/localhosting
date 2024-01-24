package site.ugaeng.localhostingserver.forward;

import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.http.response.Response;
import site.ugaeng.localhostingserver.tunnel.domain.Tunnel;

import java.io.IOException;

public interface RequestForwarder {

    Response forwardRequestForTunnel(Tunnel tunnel, Request request) throws IOException;
}
