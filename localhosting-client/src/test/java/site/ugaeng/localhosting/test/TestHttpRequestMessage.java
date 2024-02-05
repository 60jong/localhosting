package site.ugaeng.localhosting.test;

public class TestHttpRequestMessage {

    public static final String HTTP_REQUEST_MESSAGE_GET =
            "GET /posts HTTP/1.1\r\n" +
                    "User-Agent: PostmanRuntime/7.36.1\n\n" +
                    "Accept: */*\n\n" +
                    "Cache-Control: no-cache\n\n" +
                    "Host: localhost:8888\n\n" +
                    "Connection: keep-alive\n\n" +
                    "\n";

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
    public static final String HTTP_REQUEST_MESSAGE_GET_JSON =
            "{\"requestLine\":{\"method\":\"GET\",\"uri\":\"/posts\",\"version\":\"HTTP_1_1\"},\"headers\":{\"Host\":\"localhost:8888\"},\"entity\":\"\"}";


    public static final String HTTP_REQUEST_MESSAGE_POST_JSON =
            "{\n" +
            "  \"requestLine\":{\n" +
            "    \"method\":\"POST\",\n" +
            "    \"uri\":\"/ugaeng/host\",\n" +
            "    \"version\":\"HTTP_1_1\"\n" +
            "  },\n" +
            "  \"headers\":{\n" +
            "    \"content-length\":\"33\",\n" +
            "    \"postman-token\":\"990857a6-7b27-433b-85d8-01ae1e1b9cf9\",\n" +
            "    \"host\":\"localhost:8080\",\n" +
            "    \"content-type\":\"application/json\",\n" +
            "    \"connection\":\"keep-alive\",\n" +
            "    \"cache-control\":\"no-cache\",\n" +
            "    \"accept-encoding\":\"gzip, deflate, br\",\n" +
            "    \"user-agent\":\"PostmanRuntime/7.36.1\",\n" +
            "    \"accept\":\"*/*\"\n" +
            "  },\n" +
            "  \"entity\":\"{\\r\\n    \"domainName\" : \"ugaeng\"\\r\\n}\"\n" +
            "}";

    public static final String HTTP_RESPONSE_HTML =
            "{\n" +
            "  \"statusLine\":{\n" +
            "    \"version\":\"HTTP_1_1\",\n" +
            "    \"statusCode\":200,\n" +
            "    \"reasonPhrase\":\"OK\"\n" +
            "  },\n" +
            "  \"headers\":{\n" +
            "    \"Content-Length\":\"192\",\n" +
            "    \"Content-Type\":\"text/html\"\n" +
            "  },\n" +
            "  \"entity\":\"<!DOCTYPE html>\\n<html>\\n<head>\\n<meta charset=\\\"UTF-8\\\">\\n<title>UGAENG</title>\\n</head>\\n<body>\\n<div class=\\\"container\\\">\\n<h1> UGAENG </h1>\\nINDEX\\n</div>\\n</body>\\n</html>\"\n" +
            "}";
}
