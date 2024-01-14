package site.ugaeng.localhosting.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void null_string() {
        // given
        String text = null;

        // when
        boolean hasText = StringUtils.hasText(text);

        // then
        Assertions.assertThat(hasText).isFalse();
    }

    @Test
    void empty_string() {
        // given
        String text = "";

        // when
        boolean hasText = StringUtils.hasText(text);

        // then
        Assertions.assertThat(hasText).isFalse();
    }

    @Test
    void valid_string() {
        // given
        String text = "text";

        // when
        boolean hasText = StringUtils.hasText(text);

        // then
        Assertions.assertThat(hasText).isTrue();
    }

    @Test
    void test() {
        // given
        String a = "POST /ugaeng/host HTTP/1.1\r\nContent-Type: application/json\r\nUser-Agent: PostmanRuntime/7.36.1\r\nHost: localhost:8080\r\nContent-Length: 33\r\n\r\n{\r\n    \"domainName\" : \"ugaeng\"\r\n}";
        // when

        // then

    }
}