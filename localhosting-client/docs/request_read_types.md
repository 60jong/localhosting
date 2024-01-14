### SERIALIZED_REQUEST 
```json
{
  "type":"SERIALIZED_REQUEST",
  "data":{
    "requestLine":{
      "method":"POST",
      "uri":"/ugaeng/host",
      "version":"HTTP_1_1"
    },
    "headers":{
      "content-length":"33",
      "postman-token":"990857a6-7b27-433b-85d8-01ae1e1b9cf9",
      "host":"localhost:8080",
      "content-type":"application/json",
      "connection":"keep-alive",
      "cache-control":"no-cache",
      "accept-encoding":"gzip, deflate, br",
      "user-agent":"PostmanRuntime/7.36.1",
      "accept":"*/*"
    },
    "entity":"{\r\n \"domainName\" : \"ugaeng\"\r\n}"
  }
}
```

### NATIVE_HTTP_REQUEST
```json
{
  "type":"NATIVE_HTTP_REQUEST",
  "data":"POST /ugaeng/host HTTP/1.1\r\nContent-Type: application/json\r\nUser-Agent: PostmanRuntime/7.36.1\r\nHost: localhost:8080\r\nContent-Length: 33\r\n\r\n{\r\n    \"domainName\" : \"ugaeng\"\r\n}"
}
```
