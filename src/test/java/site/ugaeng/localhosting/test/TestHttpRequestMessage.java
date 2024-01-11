package site.ugaeng.localhosting.test;

import java.io.BufferedReader;

public class TestHttpRequestMessage {

    public static final String HTTP_REQUEST_MESSAGE_GET =
            "GET /posts HTTP/1.1\r\n" +
            "Content-Type: text/html\r\n" +
            "Content-Language: ko-KR\r\n" +
            "\r\n";

    public static final String HTTP_REQUEST_MESSAGE_POST_en =
            "POST /test HTTP/1.1\r\n" +
            "Content-Type: application/json\r\n" +
            "Content-Length: 26\r\n" +
            "Content-Language: ko-KR\r\n" +
            "\r\n" +
            "{\r\n" +
            "    \"name\" : \"hello\"\r\n" +
            "}";


    public static final String HTTP_REQUEST_MESSAGE_POST_kr =
            "POST /test HTTP/1.1\r\n" +
            "Content-Type: application/json\r\n" +
            "Content-Length: 27\r\n" +
            "Content-Language: ko-KR\r\n" +
            "\r\n" +
            "{\r\n" +
            "    \"name\" : \"안녕\"\r\n" +
            "}";
}
