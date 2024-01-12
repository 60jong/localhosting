## Allow restricted HTTP request headers

> By default, the following request headers are not allowed to be set by user code
 in HttpRequests: "connection", "content-length", "expect", "host" and "upgrade".
 The 'jdk.httpclient.allowRestrictedHeaders' property allows one or more of these
 headers to be specified as a comma separated list to override the default restriction.
 The names are case-insensitive and white-space is ignored (removed before processing
 the list). Note, this capability is mostly intended for testing and isn't expected
 to be used in real deployments. Protocol errors or other undefined behavior is likely
 to occur when using them. The property is not set by default.
 Note also, that there may be other headers that are restricted from being set
 depending on the context. This includes the "Authorization" header when the
 relevant HttpClient has an authenticator set. These restrictions cannot be
 overridden by this property.

> jdk.httpclient.allowRestrictedHeaders=host
