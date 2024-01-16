package site.ugaeng.localhostingserver.http;

import static site.ugaeng.localhostingserver.http.HttpConstant.SP;

public class RequestLineBuilder {

    public static RequestLine build(String line) {
        final String[] elements = line.split(SP);

        final var method = elements[0];

        final var uri = elements[1];
        final var localUri =  uri;

        final var version = elements[2];

        return new RequestLine(method, localUri, ProtocolVersion.of(version));
    }
}
