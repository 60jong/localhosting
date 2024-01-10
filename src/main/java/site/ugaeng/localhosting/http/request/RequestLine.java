package site.ugaeng.localhosting.http.request;

import lombok.Builder;
import lombok.Getter;
import site.ugaeng.localhosting.http.ProtocolVersion;

import static site.ugaeng.localhosting.http.HttpConstant.SP;

@Getter
public class RequestLine {

    private String method;
    private String uri;
    private ProtocolVersion version;

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer();

        return buffer.append(method)
                     .append(SP)
                     .append(uri)
                     .append(SP)
                     .append(version.getValue())
                     .append(SP)
                     .toString();
    }

    public static Builder builder() {
        return new Builder(new RequestLine());
    }

    /* Builder Class */
    protected static class Builder {

        private final RequestLine requestLine;

        public Builder(RequestLine requestLine) {
            this.requestLine = requestLine;
        }

        public Builder method(String method) {
            requestLine.method = method;
            return this;
        }

        public Builder uri(String uri) {
            requestLine.uri = uri;
            return this;
        }

        public Builder localUri(String uri) {
            requestLine.uri = "http://localhost:" + uri;
            return this;
        }

        public Builder version(ProtocolVersion version) {
            requestLine.version = version;
            return this;
        }

        public RequestLine build() {
            return this.requestLine;
        }
    }

}
