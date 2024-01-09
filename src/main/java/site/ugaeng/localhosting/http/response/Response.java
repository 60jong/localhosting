package site.ugaeng.localhosting.http.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public final class Response {

    private StatusLine statusLine;
    private Map<String, ? extends Object> headers;
    private String entity;
}
