package site.ugaeng.localhosting.http.local.client;

import site.ugaeng.localhosting.client.LocalProcessClient;
import site.ugaeng.localhosting.http.local.request.Request;
import site.ugaeng.localhosting.http.local.response.Response;

/**
 * Apache, HttpClient
 */
public class LocalProcessApacheHttpClient implements LocalProcessClient {

    private static LocalProcessApacheHttpClient instance;

    private LocalProcessApacheHttpClient() {
    }

    public static LocalProcessApacheHttpClient getInstance() {
        if (instance == null) {
            instance = new LocalProcessApacheHttpClient();
        }

        return instance;
    }

    @Override
    public Response performRequest(Request request) {
        return null;
    }
}

