package site.ugaeng.localhosting.forward;

import site.ugaeng.localhosting.TunnelingServerConnection;
import site.ugaeng.localhosting.TunnelingServerConnector;
import site.ugaeng.localhosting.client.LocalProcessClient;
import site.ugaeng.localhosting.http.forward.HttpRequestForwardingTemplate;
import site.ugaeng.localhosting.http.local.LocalRequests;

public class RequestForwardingTemplates {

    public static HttpRequestForwardingTemplate getHttpRequestForwardingTemplate() {
        TunnelingServerConnection serverConnection = TunnelingServerConnector.getServerConnection();
        LocalProcessClient localRequestHttpClient = LocalRequests.getLocalRequestHttpClient();

        return new HttpRequestForwardingTemplate(serverConnection, localRequestHttpClient);
    }
}
