package site.ugaeng.localhostingserver.forward;

import site.ugaeng.localhostingserver.http.Request;
import site.ugaeng.localhostingserver.http.Response;
import site.ugaeng.localhostingserver.tunnel.Tunnel;

import java.io.IOException;

public interface RequestForwarder {

    Response forwardRequestForTunnel(Tunnel tunnel, Request request) throws IOException;
}
