package site.ugaeng.localhostingserver.http;

import java.util.Arrays;
import java.util.List;

public enum ProtocolVersion {

    HTTP_1_0("HTTP/1.0", List.of("HTTP/1.0", "HTTP_1_0")),
    HTTP_1_1("HTTP/1.1", List.of("HTTP/1.1", "HTTP_1_1")),
    HTTP_2("HTTP/2", List.of("HTTP/2", "HTTP_2"));

    private String mainValue;
    private List<String> values;

    ProtocolVersion(String mainValue, List<String> values) {
        this.mainValue = mainValue;
        this.values = values;
    }

    public static ProtocolVersion of(String value) {
        return Arrays.stream(ProtocolVersion.values())
                     .filter(version -> version.hasValue(value))
                     .findAny()
                     .orElse(null);
    }

    public String getValue() {
        return mainValue;
    }

    private boolean hasValue(String value) {
        return values.contains(value);
    }
}
