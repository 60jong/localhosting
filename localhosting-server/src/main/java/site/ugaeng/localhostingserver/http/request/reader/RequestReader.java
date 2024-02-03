package site.ugaeng.localhostingserver.http.request.reader;

import jakarta.servlet.http.HttpServletRequest;
import site.ugaeng.localhostingserver.http.request.Request;
import site.ugaeng.localhostingserver.http.request.RequestLine;
import site.ugaeng.localhostingserver.http.request.RequestLineBuilder;
import site.ugaeng.localhostingserver.impl.socket.io.SocketDataLineReader;
import site.ugaeng.localhostingserver.utils.SocketIOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;
import static site.ugaeng.localhostingserver.http.HttpConstant.*;
import static site.ugaeng.localhostingserver.utils.SocketIOUtils.getReader;

public class RequestReader {

    public static Request readFromHttpServletRequest(HttpServletRequest httpRequest) throws IOException {
        // HTTP 요청 라인 가져오기
        String httpRequestLine = String.join(SP,
                httpRequest.getMethod(),
                getActualUri(httpRequest),
                httpRequest.getProtocol());
        final RequestLine requestLine = RequestLineBuilder.build(httpRequestLine);

        // HTTP 요청 헤더 가져오기
        final Map<String, String> requestHeaders = new HashMap<>();
        httpRequest.getHeaderNames()
                .asIterator()
                .forEachRemaining(headerName -> {
                    requestHeaders.put(headerName, httpRequest.getHeader(headerName));
                });

        // HTTP 요청 바디 가져오기
        final String requestBody = httpRequest.getReader()
                .lines()
                .collect(Collectors.joining());

        return new Request(requestLine, requestHeaders, requestBody);
    }

    private static String getActualUri(HttpServletRequest httpRequest) {
        String originUri = httpRequest.getRequestURI();

        // TODO : getSubstringFromThirdSlash 리팩터링
        final String actualUri = getSubstringFromThirdSlash(originUri);
        final String queryString = httpRequest.getQueryString();

        return addQueryStringIfExists(actualUri, queryString);
    }

    private static String getSubstringFromThirdSlash(String originUri) {
        // [ /host/{tunnelName}/** ]
        int thirdSlashIdx = 0;
        int slashCount = 3;

        while (slashCount > 0) {
            if (originUri.charAt(thirdSlashIdx++) == '/') {
                slashCount--;
            }
        }
        thirdSlashIdx--;

        return originUri.substring(thirdSlashIdx);
    }

    private static String addQueryStringIfExists(String actualUri, String queryString) {
        return queryString == null ? actualUri : actualUri + "?" + queryString;
    }

    public static Request readFromSocket(Socket socket) {
        SocketDataLineReader reader = getReader(socket);

        final RequestLine requestLine = readRequestLine(reader);
        final Map<String, String> headers = readHeaders(reader);
        final String entity = readEntityIfContentExists(reader, headers);

        return new Request(requestLine, headers, entity);
    }

    private static RequestLine readRequestLine(SocketDataLineReader reader) {
        String firstLine = reader.readLine();

        return RequestLineBuilder.build(firstLine);
    }

    private static Map<String, String> readHeaders(SocketDataLineReader reader) {
        final Map<String, String> headers = new HashMap<>();

        String line;
        while (hasText(line = reader.readLine())) {
            String[] headerAndValue = line.split(COLON_SP);
            headers.put(headerAndValue[0], headerAndValue[1]);
        }

        return headers;
    }

    private static String readEntityIfContentExists(SocketDataLineReader reader, Map<String, String> headers) {
        if (!headers.containsKey(CONTENT_LENGTH)) {
            return "";
        }

        return readEntity(reader, headers);
    }

    private static String readEntity(SocketDataLineReader reader, Map<String, String> headers) {
        BufferedReader br = reader.getBufferedReader();
        int contentLength = Integer.parseInt(headers.get(CONTENT_LENGTH));

        char[] content = new char[contentLength];

        try {
            br.read(content, 0, contentLength);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new String(content).trim();
    }
}
