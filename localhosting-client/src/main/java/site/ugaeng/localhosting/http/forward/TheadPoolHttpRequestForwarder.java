package site.ugaeng.localhosting.http.forward;

import site.ugaeng.localhosting.client.LocalProcessClient;

public class TheadPoolHttpRequestForwarder extends HttpRequestForwarder {

    public TheadPoolHttpRequestForwarder(LocalProcessClient processClient) {
        super(processClient);
    }

}
