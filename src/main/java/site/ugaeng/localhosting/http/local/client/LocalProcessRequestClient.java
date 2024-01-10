package site.ugaeng.localhosting.http.client;

import site.ugaeng.localhosting.http.request.LocalRequest;
import site.ugaeng.localhosting.http.response.LocalResponse;

public interface LocalProcessRequestClient {

    LocalResponse performLocalRequest(LocalRequest request);
}
