package site.ugaeng.localhostingserver;

import lombok.RequiredArgsConstructor;

import java.io.OutputStream;

@RequiredArgsConstructor
public class TunnelConnection {

    private final OutputStream clientOutputStream;
}
